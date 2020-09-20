package instance;

/**
 * @description： 懒汉式
 */
public class LazyInstance {

    private static LazyInstance LazyInstance = null;

    private LazyInstance(){}

    public static synchronized LazyInstance getInstance(){
        if(null == LazyInstance){
            LazyInstance = new LazyInstance();
        }
        return LazyInstance;
    }
}
