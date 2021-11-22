package Thread2;

/**
 * @author skwang
 * @create 2021-03-13-15:58
 */
public class TestRunnable implements Runnable{

    int count = 0;
    @Override

    public void run() {
        System.out.println(Thread.currentThread().getName()+"Runnable多线程代码");
        for(int i = 0; i < 5; i++) {
            count ++;
            System.out.println("这是Runnable多线程的代码" + count);
        }
    }
}
