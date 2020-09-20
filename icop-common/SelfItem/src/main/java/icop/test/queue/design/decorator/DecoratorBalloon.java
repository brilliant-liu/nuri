package icop.test.queue.design.decorator;

/**
 * @author: liukj
 * @date: 2020/8/13
 * @description：
 */
public class DecoratorBalloon implements Action {
    private Action action ;
    public DecoratorBalloon(Action action) {
        this.action = action;
    }
    @Override
    public void balloon() {
        System.out.println("泡泡消除！");
        action.balloon();
        System.out.println("泡泡完结！");
    }
}
