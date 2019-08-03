package com.java.book.self.producer_consumer;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 7.17 通过“毒丸”对象来关闭服务
 * @ClassName: IndexingService
 * @author xingle
 * @date 2014-11-12 下午1:58:06
 */
public class IndexingService {
    private static final int CAPACITY = 1000;
    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    //private final FileFilter fileFilter;
    private final File root;

    public IndexingService(File root) {
        this.root = root;
        this.queue = new LinkedBlockingQueue<File>(CAPACITY);

    }

    private boolean alreadyIndexed(File f) {
        return false;
    }

    //生产者
    class CrawlerThread extends Thread {
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) { /* fall through */

            } finally {
                while (true) {
                    try {
                        System.out.println("放入“毒丸”");
                        queue.put(POISON);
                        break;
                    } catch (InterruptedException e1) { /* retry */
                    }
                }
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory())
                        crawl(entry);
                    else if (!alreadyIndexed(entry)){
                        System.out.println("放入生产者队列文件："+entry.getName()+" 来自线程："+Thread.currentThread().getName());
                        queue.put(entry);
                    }
                }
            }
        }
    }

    //消费者
    class IndexerThread extends Thread {
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == POISON){
                        System.out.println("遇到“毒丸”，终止");
                        break;
                    } else {
                        indexFile(file);
                    }
                }
            } catch (InterruptedException consumed) {

            }
        }

        public void indexFile(File file) {
            System.out.println("消费者取出文件："+file.getName()+" 来自线程："+Thread.currentThread().getName());
            /* ... */
        };
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }

}
