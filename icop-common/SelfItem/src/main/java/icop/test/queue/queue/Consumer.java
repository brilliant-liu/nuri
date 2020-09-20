package icop.test.queue.queue;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: liukj
 * @date: 2020/8/1
 * @description：
 */
public class Consumer {

    private int consumerCoreNum ;
    private int consumerMaxNum;
    private long keepAlice;
    private ThreadPoolExecutor threadPoolExecutor ;
    private Queue queue;

    public Consumer(Queue queue,int consumerCoreNum,int consumerMaxNum,long keepAlice){
        this.queue = queue;
        this.consumerCoreNum = consumerCoreNum;
        this.consumerMaxNum = consumerMaxNum;
        this.keepAlice = keepAlice;

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("consumer-pool-%d").build();

        threadPoolExecutor = new ThreadPoolExecutor(consumerCoreNum, consumerMaxNum,
                keepAlice, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(1200), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    public void consumer(){
        while (true){
            if(queue.size() >0){
                threadPoolExecutor.execute(()-> {
                    // 当前线程睡眠三秒，模拟业务处理用时
                    try {
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "消费者获取到消息："+ queue.poll());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
            }
        }


    }

}
