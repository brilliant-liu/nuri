package icop.test.queue.queue;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author: liukj
 * @date: 2020/7/31
 * @description：
 */
public class Producer {

    private Queue queue;

    public Producer(Queue queue){
        this.queue =queue;
    }
    public void inputMessage (){
       int i =0;
       while (i<1000){
           /*try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }*/
           queue.put("我是第"+(i++)+"个消息哦！");
       }
    }
}
