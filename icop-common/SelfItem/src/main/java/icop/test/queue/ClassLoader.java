package icop.test.queue;

/**
 * @author: liukj
 * @date: 2020/8/1
 * @description： 类加载的顺序
 */
public class ClassLoader {

    /**
     * 注意这里再内部初始了一下自己,且其是静态的，所以类加载的时候会首先处理它，
     * 即创建一个对象（这里只会处理实例变量，构造代码块和构造方法）。
     */
    private static int num1 = 5;
    private static ClassLoader classLoader = new ClassLoader();
    /**
     * 注意num1和num2的区别
     *
     * 这里是静态变量，会按照初始化的顺序加载，所以上面对象创建时，这里还没有被加载，会默认为0;
     */
    private static int num2 = 5;
    private static String  obj = new String ("1023");
    private String str = "9";

    {
        System.out.println("{} = 开始============");
        System.out.println("num1:"+num1);
        System.out.println("num2:"+num2);
        System.out.println("str:"+str);
        System.out.println("obj:"+obj);
        System.out.println("{} = 结束============");
    }

    // 它是随着类的加载而执行，只执行一次，并优先于主函数
    static {
        System.out.println("static = 开始============");
        System.out.println("num1:"+num1);
        System.out.println("num2:"+num2);
        System.out.println("obj:"+obj);
        System.out.println("static = 结束============");
    }

    private ClassLoader(){
        System.out.println("开始执行构造方法==========");
    }

    public static ClassLoader getInstance(){
        return classLoader;
    }

    public void instanceMethod(){
        System.out.println("执行 instanceMethods ..............");
    }

    public static void main(String[] args) {
        ClassLoader.getInstance().instanceMethod();
    }
}
