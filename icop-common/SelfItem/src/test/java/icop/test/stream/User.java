package icop.test.stream;

import java.io.Serializable;

/**
 * @author: liukj
 * @date: 2020/8/19
 * @description：
 */
public class User implements Serializable {
    private static final long serialVersionUID = -5461410013475223014L;

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
