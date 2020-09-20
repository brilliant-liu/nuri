package icop.test.queue.initSequence;

/**
 * @author: liukj
 * @date: 2020/8/10
 * @description：
 */
public class Animal {

    private static int age = 1;

    public Animal(){

        System.out.println("初始化父类~"+"age:"+age);
    }

    static {
        System.out.println("初始化父类静态方法~"+"age:"+age);
    }

}
