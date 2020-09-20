package icop.test.queue.initSequence;

/**
 * @author: liukj
 * @date: 2020/8/10
 * @description：
 */
public class Cat extends Animal {
    int foot;
    private static String eye = "2";

    Cat(){
        System.out.println("初始化猫！"+"eye:"+eye+" foot:"+foot);
    }

    static {
        System.out.println("初始化猫静态方法！"+"eye:"+eye);
    }

}
