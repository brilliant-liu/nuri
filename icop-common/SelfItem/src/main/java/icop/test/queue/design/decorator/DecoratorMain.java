package icop.test.queue.design.decorator;

/**
 * @author: liukj
 * @date: 2020/8/13
 * @description：
 */
public class DecoratorMain {
    public static void main(String[] args) {
        Action defaultBalloon = new Balloon();
        Action action = new DecoratorBalloon(defaultBalloon);
        action.balloon();
    }
}
