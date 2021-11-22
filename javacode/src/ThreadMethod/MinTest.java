package ThreadMethod;

/**
 * @author skwang
 * @create 2021-03-13-16:24
 */
public class MinTest {
    public static void main(String[] args)  {
        RunnableTest run0 = new RunnableTest();
        RunnableTest run1 = new RunnableTest();

        Thread t0 = new Thread(run0);
        Thread t1 = new Thread(run1);



        /**
         * 线程优先级 1-10 越大越高  默认的是5
         *
         */
        t0.setPriority(5);

        t1.setPriority(5);
        System.out.println("t0的优先级： " + t0.getPriority());
        t0.start();//启动线程
        t1.start();
        t0.setName("线程t0:");//设置线程名称
        t1.setName("线程t1:");//设置线程名称
        System.out.println(t0.getName());
        System.out.println(t1.getName());
        t1.stop();//强制结束线程生命
        System.out.println(t1.isAlive());
        System.out.println(t0.isAlive());
        System.out.println("=========================");
        System.out.println("=========================");
        try {
            t0.join();//t0的run查到这个位置执行
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("=========================");
    }
}
