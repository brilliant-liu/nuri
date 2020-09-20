package icop.test.queue.design.decorator;

/**
 * @author: liukj
 * @date: 2020/8/13
 * @description：
 */
public class Balloon implements Action {
    @Override
    public void balloon() {
        System.out.println("冒泡泡~");
    }
}
