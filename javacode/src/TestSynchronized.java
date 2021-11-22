/**
 * @author skwang
 * @create 2021-03-13-18:42
 * 线程共享资源，交叉执行 加锁
 * 在方法上加synchronized关键字
 *
 *
 * 在方法上加synchronized关键字，锁的是当前对象
 *静态方法加synchronized，所有对象共用一个锁
 *代码块加同步锁：1：synchronized（this）所有对象共用一个所
 * synchronized（a）  synchronized(a1)
 *
 */
public class TestSynchronized {
    public static void main(String[] args) {
        Acount a = new Acount();
        Acount a1 = new Acount();
        User u_weixin = new User(a, 2000);
        User u_zhifubao = new User(a, 2000);
        Thread weixin = new Thread(u_weixin, "微信");
        Thread zhifubao = new Thread(u_zhifubao, "支付宝");

        weixin.start();
        zhifubao.start();
    }
}
class Acount {
    public static int money = 3000;

    /**
     *  * 线程共享资源，交叉执行 加锁
     *  * 在方法上加synchronized关键字，锁的是当前对象，对象不同，锁失效，静态的方法加锁对所有的对象都是同一个锁
     *  synchronized可以锁代码块  synchronized（this）{  。。。} ;   this 表示当前的对象
     * @param m
     *
     *
     */
    public synchronized void drawing(int m) {
        String name = Thread.currentThread().getName();
        if(money < m) {
            System.out.println(name + "操作，现有金额不足"+ m);
        }else {

            System.out.println(name + "操作，账户原有金额：" + money);
            System.out.println(name + "操作，取款金额：" + m);

            money = money - m;
            System.out.println(name + "操作，取款后的金额：" + money);
        }
    }

    public synchronized void drawing1(int m) {
        String name = Thread.currentThread().getName();
        if(money < m) {
            System.out.println(name + "操作，现有金额不足"+ m);
        }else {

            System.out.println(name + "操作，账户原有金额：" + money);
            System.out.println(name + "操作，取款金额：" + m);

            money = money - m;
            System.out.println(name + "操作，取款后的金额：" + money);
        }
    }

    public static synchronized void drawing2(int m) {
        String name = Thread.currentThread().getName();
        if(money < m) {
            System.out.println(name + "操作，现有金额不足"+ m);
        }else {

            System.out.println(name + "操作，账户原有金额：" + money);
            System.out.println(name + "操作，取款金额：" + m);

            money = money - m;
            System.out.println(name + "操作，取款后的金额：" + money);
        }
    }

    /**
     * wait() notify() notifyAll()这三个方法只能用在有锁得代码中
     * @param m
     * @param a
     */
    public void drawing3(int m, Acount a) {
        synchronized (a) {
            String name = Thread.currentThread().getName();
            if(name.equals("微信")) {
                try {
                    a.wait();//微信线程阻塞态
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (money < m) {
                System.out.println(name + "操作，现有金额不足" + m);
            } else {

                System.out.println(name + "操作，账户原有金额：" + money);
                System.out.println(name + "操作，取款金额：" + m);

                money = money - m;
                System.out.println(name + "操作，取款后的金额：" + money);
            }

            if(name.equals("支付宝")) {
                a.notify();//唤醒当前优先级最高的线程
//                a.notifyAll();
            }
        }
    }
}

class User implements Runnable {
    Acount acount;
    int money;
    public User(Acount acount, int money) {
        this.acount = acount;
        this.money = money;
    }
    @Override
    public void run() {
//        if(Thread.currentThread().getName().equals("微信") ) {
//            acount.drawing(money);
//        }
//        else{
//            acount.drawing2(money);
//        }
        acount.drawing3(money, acount);
    }
}