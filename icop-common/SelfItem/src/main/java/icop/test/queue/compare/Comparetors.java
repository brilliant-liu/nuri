package icop.test.queue.compare;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liukj
 * @date: 2020/8/7
 * @description：
 */
public class Comparetors {

    public static void main(String[] args) {
        Cat cat1 = new Cat(30,20);
        Cat cat3 = new Cat(90,90);
        Cat cat2 = new Cat(20,40);

        List<Cat> list = new ArrayList<>();
        list.add(cat2);
        list.add(cat3);
        list.add(cat1);

        System.out.println(list.toString());
        list.sort(Cat::compareTo);
        System.out.println(list.toString());



    }

}
