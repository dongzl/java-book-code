package com.java.book.self.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * @author dongzonglei
 * @description
 * @date 2019-08-06 16:17
 */
public class DisruptorTest {

    // 自定义 Event
    static class LongEvent {
        private long value;
        public void set(long value) {
            this.value = value;
        }
    }

    public static void main(String args[]) throws Exception {
        // 指定RingBuffer大小
        // 必须是 2 的 N 次方
        int bufferSize = 1024;

        // 构建 Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent::new,
                bufferSize, DaemonThreadFactory.INSTANCE);

        // 注册事件处理器
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("E: " + event));

        // 启动 disruptor
        disruptor.start();

        // 获取 RingBuffer
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // 生产 Event
        ByteBuffer bb = ByteBuffer.allocate(8);

        for (long l = 0; true; l++) {
            bb.putLong(0 ,l);
            // 生产者生产消息
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
            Thread.sleep(1000);
        }
    }
}
