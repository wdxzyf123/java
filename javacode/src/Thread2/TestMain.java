package Thread2;

/**
 * @author skwang
 * @create 2021-03-13-15:55
 */
public class TestMain {
    public static void main(String[] args) {
//        Thread t1 = new Thread(new TestRunnable());
        Runnable run = new TestRunnable();
        Thread t1 = new Thread(run, "t1-");
        Thread t2 = new Thread(run, "t2-") ;
        t1.start();
        System.out.println("=======================");
        System.out.println("=======================");
        System.out.println("=======================");
        System.out.println("=======================");
        System.out.println("=======================");
        System.out.println("=======================");
        t2.start();
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("----------------------------");
    }
}
