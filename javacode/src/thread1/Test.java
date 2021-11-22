package thread1;

/**
 * @author skwang
 * @create 2021-03-13-15:32
 */
public class Test {
    public static void main(String[] args) {
        TestThread t0 = new TestThread();
        TestThread t1 = new TestThread();
        TestThread t2 = new TestThread();
        TestThread t3 = new TestThread();
        System.out.println("-----------------");
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println("=====================");
        t0.start();
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        t1.start();
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        t2.start();
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        t3.start();
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("-----------------");


    }
}
