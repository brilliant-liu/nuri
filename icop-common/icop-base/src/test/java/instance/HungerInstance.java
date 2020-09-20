package instance;

/**
 * @description： 饿汉式单例模式
 */
public class HungerInstance {
    private static HungerInstance hungerInstance = new HungerInstance();

    private HungerInstance(){}

    public static  HungerInstance getInstance(){
        return hungerInstance;
    }
}
