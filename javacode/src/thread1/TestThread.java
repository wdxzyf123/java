package thread1;

/**
 * @author skwang
 * @create 2021-03-13-15:35
 * 继承Thread类实现多线程
 */
public class TestThread extends Thread{
    @Override//ctrl + o
    public void run() {
        System.out.println("多线程代码");
        for(int i =0; i < 5; i++) {
            System.out.println("多线程逻辑" + i);
        }
    }
}
