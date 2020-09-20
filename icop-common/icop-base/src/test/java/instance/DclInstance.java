package instance;

/**
 * @description： 双重锁定检查（DCL,Double Check Lock）单例模式写法
 */
public class DclInstance {
    private static volatile DclInstance instance = null;

    private DclInstance(){}

    public static DclInstance getInstance(){
        if(null == instance){
            synchronized (DclInstance.class){
                if( null == instance ){
                    instance = new DclInstance();
                }
            }
        }
        return instance;
    }
}
