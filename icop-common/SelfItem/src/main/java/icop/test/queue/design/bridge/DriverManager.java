package icop.test.queue.design.bridge;

/**
 * @author: liukj
 * @date: 2020/8/14
 * @description：
 */
public abstract class DriverManager  {

    private Driver driver;

    DriverManager(Driver driver){ }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }


}
