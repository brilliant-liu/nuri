package icop.test.queue.design.bridge;

/**
 * @author: liukj
 * @date: 2020/8/14
 * @description：
 */
public class OracleDriver implements Driver{
    @Override
    public void getConnection() {
        System.out.println("Oracle获取连接成功~");
    }
}
