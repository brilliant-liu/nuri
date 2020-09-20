import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: liukj
 * @date: 2020/7/28
 * @description：
 */
public class ThreadTestMain {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,2L, TimeUnit.MINUTES,new LinkedBlockingDeque());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future<Object> submit = threadPoolExecutor.submit(() -> "这个是要输出的内容！！！");
        Object o = submit.get();
        System.out.println(o.toString());

    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        Future<Object> submit = threadPoolExecutor.submit(() -> "sd********！");
        Object o = submit.get();
        System.out.println(o.toString());
    }


}
