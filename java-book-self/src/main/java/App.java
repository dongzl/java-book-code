import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author dongzonglei
 * @description
 * @date 2020/5/22 上午10:21
 */
class App {
//    ExecutorService executor = ...
//    ArchiveSearcher searcher = ...
//    void showSearch(final String target) throws InterruptedException {
//        
//        Future<String> future = executor.submit(new Callable<String>() {
//            public String call() {
//                return searcher.search(target);
//            }});
//        displayOtherThings(); // do other things while searching
//        try {
//            displayText(future.get()); // use future 
//        } catch (ExecutionException ex) { 
//            cleanup(); 
//            return; 
//        }
//    
//        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
//            public String call() {
//                return searcher.search(target);
//            }
//        });
//        executor.execute(future);
//    }
}
