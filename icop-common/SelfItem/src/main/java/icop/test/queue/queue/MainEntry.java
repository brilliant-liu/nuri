package icop.test.queue.queue;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: liukj
 * @date: 2020/8/1
 * @description：
 */
public class MainEntry {

    public static void main(String[] args) {
        Queue queue = Queue.getQueue();

        //定义一个栅栏，等待消费者和生产者都初始化成功后，再运行各自的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1);

        // 启动一个生产者
        Thread producerThread = new Thread(()->{
            Producer producer = new Producer(queue);
            try {
                System.out.println("生产者初始化成功,等待消费者初始化...");
                cyclicBarrier.await();
                System.out.println("开始自动生成消息..");
                producer.inputMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        });

        Thread comsumerThread = new Thread(()->{
            // 启动一个消费者，内部使用线程池多线程处理数据
            Consumer consumer = new Consumer(queue,5,10,1000);
            System.out.println("等待生产者初始化...");
            try {
                cyclicBarrier.await();
                System.out.println("开始消费...");
                consumer.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        });
        producerThread.start();
        comsumerThread.start();


    }
}
