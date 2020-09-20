package icop.test.queue.design.bridge;

/**
 * @author: liukj
 * @date: 2020/8/14
 * @description：
 */
public class MyBridge  extends  DriverManager{

    MyBridge(Driver driver) {
        super(driver);
    }

    public void getConnection(){
        getDriver().getConnection();
    }

}
