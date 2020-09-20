import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: liukj
 * @date: 2020/7/30
 * @description：
 */
public class QueueThreads {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,10, TimeUnit.MINUTES,new LinkedBlockingQueue());

    public void serverQueue(String message){
        threadPoolExecutor.submit(new ConsumerTask(message));
    }

    public static void main(String[] args) {
        QueueThreads queueThreads = new QueueThreads();

        queueThreads.consumer();
    }

    public void consumer(){
        for (int i = 0; i < 20; i++) {

            serverQueue("消息"+i);
        }

    }
    public  class ConsumerTask implements Runnable {

        private String content;
        public ConsumerTask(String content){
            this.content = content;
        }
        @Override
        public void run() {
            System.out.println("消费者："+Thread.currentThread().getName()+"开始消费："+content);
        }

    }
}
