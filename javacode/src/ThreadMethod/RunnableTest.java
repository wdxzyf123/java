package ThreadMethod;

/**
 * @author skwang
 * @create 2021-03-13-16:22
 */
public class RunnableTest implements Runnable {
    int count = 0;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "Runnable多线程代码");
        for (int i = 0; i < 5; i++) {
//            if(i % 2 ==0) {
//                System.out.println(i);
//                System.out.println(Thread.currentThread().getName()+ "要让了");
//                Thread.yield();//当前线程让步
//            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            count++;
            System.out.println(Thread.currentThread().getName()+"这是Runnable多线程的代码" + count);
        }
    }
}

