package icop.test.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: liukj
 * @date: 2020/8/19
 * @description：
 */
public class StreamTest {


    @Test
    public void test(){

        Map<String,User> map = new HashMap<>();
        map.put("1",new User("小俊",18));
        map.put("1",new User("小旺",17));
        map.put("1",new User("小孙",20));

        List<User> list = new ArrayList<>();
        list.add(new User("小周",21));
        list.add(new User("小杰",22));
        list.add(new User("小伦",20));

        list.stream().filter(t ->{
            /*try{
                throw new RuntimeException("9999");
            }catch (Exception e){
                System.out.println("99999999999999");
            }*/
            //int i = 10/0;
            return  true;
        }).forEach(user -> System.out.println(user.toString()));

        List<User> collect = list.stream().map()



        List<User> collect = list.stream()
                .filter(t -> t.getAge() != 21)
                .map(user -> {
                    if (user.getAge() == 20) {
                        user.setAge(100);
                        user.setName(user.getName() + 100);
                    } else {
                        user.setAge(99);
                        user.setName(user.getName() + 10);
                    }
                    return user;
                })
                .collect(Collectors.toList());

        List<User> collects = list.stream()
                .filter(t -> t.getAge() != 21)
                .peek(user -> {
                    if (user.getAge() == 20) {
                        user.setAge(1);
                        user.setName(user.getName() + 1);
                    } else {
                        user.setAge(9);
                        user.setName(user.getName() + 1);
                    }
                })
                .collect(Collectors.toList());

        System.out.println(collect.toString());
        System.out.println(collects.toString());

    }
}
