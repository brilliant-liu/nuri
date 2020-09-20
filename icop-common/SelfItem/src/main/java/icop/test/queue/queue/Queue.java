package icop.test.queue.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: liukj
 * @date: 2020/7/31
 * @description：
 */
public class Queue{


    private  LinkedBlockingQueue<String> blockQueue = new LinkedBlockingQueue<>();
    private static Queue queue = null;

    public static Queue getQueue(){
        if(queue == null){
            synchronized (Queue.class){
                if(queue == null){
                    queue = new Queue();
                }
            }
        }
        return queue;
    }

    public int size(){
       return queue.blockQueue.size();
    }

    public void put(String message){
        blockQueue.add(message);
    }

    public Object poll(){
        return blockQueue.poll();
    }

    public String remove(){
        return blockQueue.remove();
    }
}
