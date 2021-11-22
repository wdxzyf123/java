#### 1. 并行和并发有什么区别？

并行是指两个或者多个事件在统一时刻发送；而并发是指两个或多个时间在同一时间间隔发送。

并行是在不同实体上的多个事件，并发是在同一实体上的多个时间。

在一台处理器上“同时”处理多个任务，在多台处理器上同时处理多个任务。如hadoop分布式集群。

所以并发编程的目的是充分地利用处理器的每个核，以达到最高的处理性能。

#### 2. 线程和进程的区别？

进程：

- 程序由指令和数据组成，但这些指令要运行，数据要读写，就必要将指令加载至CPU，数据加载至内存。在指令运行过程中还需要用到磁盘、网络等设备。进程就是用来加载指令、管理内存、管理IO的；
- 当一个程序被运行，从磁盘加载这个程序的代码至内存，这时就开启了一个进程；
- 进程就可以视为程序的一个实例。大部分程序可以同时运行多个实例进程（例如记事本、画图、浏览器等），也有的程序只能运行一个实例进程（例如网易云音乐、360安全卫士等）

线程：

- 一个进程可以分为一多多个线程；
- 一个线程就是一个指令流，将指令流的一条条指令以一定的顺序交给CPU执行；
- Java中，线程作为最小调度单位，进程作为资源分配的最小单位。在windows中进程是不可活动的，只是作为线程的容器。

二者对比：

- 进程基本上相互独立地，而线程存在于进程内，是进程的一个子集；
- 进程拥有共享的资源，如内存空间等，供其内部的线程共享；
- 进程间的通信较为复杂：
  - 同一台计算机的进程通讯称为IPC(Inter-process communication)；
  - 不同计算机之间的进程通讯，需要通过网络，并遵守共同的协议，例如HTTP
- 线程通信相对简单，因为它们共享进程内的内存，一个例子是多个线程可以访问同一个共享变量；
- 线程更轻量，线程上下文切换成本一般比进程要低

#### 3. 守护线程是什么？

守护线程（即daemon thread），是一个服务线程，准确地说就是服务其他的线程。只要其他非守护线程运行结束了，即使在守护线程的代码没有执行完，也会强制结束。

> 注意：
>
> - 垃圾回收器线程是一种守护线程；
> - Tomcat中的Acceptor和Poller线程都是守护线程，所以Tomcat接收到shutdown命令后，不会等待它们处理完当前请求。



#### 4. 创建线程有哪几种方式？

①、继承Thread类创建线程类

定义Thread类的子类，并重写该类的run方法，该run方法的方法体就代表了线程要完成的任务。因此把run()方法称为执行体。

创建Thread子类的实例，即创建了线程对象。

调用了线程对象的start()方法来启动该线程对象。

```java
// 构造方法的参数是给线程指定名字，推荐
Thread t1 = new Thread("t1") {
	@Override
	// run 方法内实现了要执行的任务
	public void run() {
		log.debug("hello");
    }
};
t1.start();
```

②、通过Runnable接口创建线程类

定义runnable接口的实现类，并重写该接口的run()方法，该run()方法提同样是该线程的线程执行体。

创建Runnable实现类的实例，并依次实例作为Thread的target来创建Thread对象，该Thread对象才是真正的线程。

调用线程对象的start()方法来启动该线程。

```java
// 创建任务对象
Runnable task2 = new Runnable() {
	@Override
	public void run() {
		log.debug("hello");
	}
};
// 参数1 是任务对象; 参数2 是线程名字，推荐
Thread t2 = new Thread(task2, "t2");
t2.start();
```

③、通过Callable和Future创建线程

创建Callable接口的实现类，并实现call()方法，该call()方法作为线程执行体，并且有返回值。

创建Callable实现类的实例，使用FutureTask类来包装Callable对象，该FutureTask封装了该Callable对象的call()方法的返回值。

使用FutureTask对象作为Thread对象的target创建并启动线程。

调用FutureTask对象的get()方法来获得子线程执行后的返回值。

```java
log.debug("开始运行...");
Thread t1 = new Thread(() -> {
	log.debug("开始运行...");
	sleep(2);
	log.debug("运行结束...");
}, "daemon");
// 设置该线程为守护线程
t1.setDaemon(true);
t1.start();

sleep(1);
log.debug("运行结束...");
```

#### 5. Thread与Runnable的关系

- Thread是将线程和任务结合在一起，Runnable是把线程和任务分开了；
- 用Runnable更容易与线程池等高级API配合；
- 用Runnable让任务类脱离了Thread继承体系，更灵活

#### 6. 说一下runnable和callable有什么区别？

Runnable接口中的run()方法的返回值是void，它做的事情纯粹是执行run()方法中的代码而已；

Callable接口中的call()方法是有返回值的，是一个泛型，和Future、FutureTask配合可以用来获取异步执行的结果。

#### 7. 线程有哪些状态？

<img src="C:\Users\cuimaolin\AppData\Roaming\Typora\typora-user-images\image-20210107163212357.png" alt="image-20210107163212357" style="zoom:70%;" />

线程通常有五种状态，创建、就绪（可运行）、运行、阻塞和死亡。

创建状态。在生成线程对象，并没有调用该线程对象的start()方法，这是线程处于创建状态。

就绪（可运行）状态。当调用了线程对象的start方法之后，该线程就进入了就绪状态，但是此时线程调度程序还没有把该线程设置为当前线程，此时处于就绪状态。在线程运行之后，从等待或者睡眠中回来之后，也会处于就绪状态。

运行状态。线程调度程序将处于就绪状态的线程设置为当前线程，此时线程就进入了运行状态，开始运行run函数当中的代码。

阻塞状态。线程正在运行的时候，被暂停，通常是为了等待某个时间的发生（比如说某项资源就绪）之后再继续运行。sleep, suspend, wait等方法都可以导致线程阻塞。

死亡状态。如果一个线程的run方法执行结束或者调用stop方法后，该线程就会死亡。对于已经死亡的线程，无法再使用start方法令其进入就绪。

#### 8. Java的线程有哪些状态

<img src="C:\Users\cuimaolin\AppData\Roaming\Typora\typora-user-images\image-20201229191400181.png" alt="image-20201229191400181" style="zoom:80%;" />

假设有线程`Thread t`

##### 情况 1 `NEW --> RUNNABLE`

- 当调用`t.start()`方法时，由`NEW --> RUNNABLE`

##### 情况 2 `RUNNABLE <--> WAITTING`

**t线程** 用`synchronized(obj)`获取对象锁后

- 调用`obj.wait()`方法，**t线程**从`RUNNABLE <--> WAITTING`
- 调用`obj.notify()`, `obj.notifyAll()`, `t.interrupt()`时
  - 竞争锁成功，**t线程**从`WAITTING --> RUNNABLE`
  - 竞争锁失败，**t线程**从`WAITTING --> BLOCKED`

##### 情况 3 `RUNNABLE <--> WAITING`

- **当前线程**调用`t.join()`方法时，**当前线程**从`RUNNABLE --> WAITTING`
  - 注意是**当前线程**在**t线程对象**的监视器上等待
- **t线程**运行结束，或调用了**当前线程**的`interrupt()`是，**当前线程**从`WAITTING --> RUNNABLE`

##### 情况 4 `RUNNABLE <--> WAITING`

- **当前线程**调用`LockSupport.park()`方法会让**当前线程**从`RUNNABLE --> WAITTING`
- 调用`LockSupport.unpark(目标线程)`或调用线程的`interrpt()`，会让目标线程从`WAITTING --> RUNNABLE`

##### 情况 5 `RUNNABLE <--> WAITTING`

**t线程** 用`synchronized(obj)`获取对象锁后

- 调用`obj.wait(long n)`方法，**t线程**从`RUNNABLE <--> TIMED_WAITTING`
- **t线程**等待时间超过了n毫秒，或调用`obj.notify()`, `obj.notifyAll()`, `t.interrupt()`时
  - 竞争锁成功，**t线程**从`TIMED_WAITTING --> RUNNABLE`
  - 竞争锁失败，**t线程**从`TIMED_WAITTING --> BLOCKED`

##### 情况 6 `RUNNABLE <--> WAITING`

- **当前线程**调用`t.join(long n)`方法时，**当前线程**从`RUNNABLE --> TIMED_WAITTING`
  - 注意是**当前线程**在**t线程对象**的监视器上等待
- **当前线程**等待时间超过了n毫秒，**t线程**运行结束，或调用了**当前线程**的`interrupt()`是，**当前线程**从`TIMED_WAITTING --> RUNNABLE`

##### 情况 7 `RUNNABLE <--> TIMED_WAITTING`

- **当前线程**调用`Thread.sleep(long n)`，**当前线程**从`RUNNABLE --> TIMED_WAITING`
- **当前线程**等待时间超过n毫秒，**当前线程**从`TIMED_WAITTING --> RUNNABLE`

##### 情况 8 `RUNNABLE <--> WAITING`

- **当前线程**调用`LockSupport.parkNanos(long nanos)`或`LockSupport.partUntil(long millis)`时，**当前线程**从`RUNNABLE --> TIMED_WAITTING`
- 调用`LockSupport.unpark(目标线程)`或调用线程的`interrpt()`，或是超时等待，会让目标线程从`TIMED_WAITTING --> RUNNABLE`

##### 情况 9 `RUNNABLE <--> BLOCKED`

- **t线程**用`synchronized(obj)`获取了对象锁时如果竞争失败，从`RUNNABLE --> BLOCKED`
- 持obj锁线程的同步代码执行完毕，会唤醒该对象上所有`BLOCKED`的线程重新竞争，如果其中**t线程**竞争成功，从`BLOCKED --> RUNNABLE`，其他失败的线程仍然`BLOCKED`

##### 情况 10 `RUNNABLE <--> TERMINATED`

当前线程所有代码运行完毕，进入`TERMINATED`

#### 9. sleep()和wait()有什么区别？

sleep()：方法是线程类(Thread)的静态党法，让调用的线程进入睡眠状态，让出执行机会给其他线程，等到休眠时间结束后，线程进行就绪状态就会和其他线程一起竞争cpu的执行时间。因为sleep()是static静态的方法，他不能改变对象的机锁，当一个synchronized块中调用了sleep()方法，线程虽然进入休眠，但是对象的机锁没有被释放，其他线程仍然无法访问这个对象。

wait()：wait()是Object类的方法，当一个线程执行到wait方法时，它就进入到一个和该对象相关的等待池，同时释放对象的机锁，使其他线程能够访问，可以通过notify, notifyAll方法来唤醒等待的线程

#### 10. notify()和notifyAll()有什么区别

如果线程调用了对象的wait()方法，那么线程边会出于该对象的等待池中，等待池钟的线程不会去竞争该对象的锁。

当有对象调用了对象的notifyAll()方法（唤醒所有wait线程）或者notify()方法（只随机唤醒一个wait线程），被唤醒的线程便会进入该线程的锁池中，锁池中的线程会去竞争该对象锁。也就是说，调用了notify后只要一个线程会由等待池进入锁池，而notifyAll()会将该对象等待池内的所有线程移动到锁池中，等待锁竞争。

优先级高的线程竞争到对象锁的概率大，假若某线程没有竞争到该对象锁，它还会留在锁池中，唯有线程再次调用wait()方法，它才会重新回到等待池中。而竞争到对象锁则继续往下执行，直到执行完了synchronized代码块，它会释放掉该对象锁，这时锁池中的线程会继续竞争该对象锁。

#### 11. 线程的run()和start()有什么区别

每个线程都是通过某个特定的Thread对象所对应的run()来完成操作的，方法run()称为线程体()，是线程任务的运行者。通过调用Thread类的start()方法来启动一个线程。

start()方法来启动一个线程，真正实现了多线程运行。这时无需等待run方法代码执行完毕，可以直接继续执行下面的代码； 这时此线程是处于就绪状态，并没有运行。然后通过此Thread类调用方法run()来完成其运行状态，这里方法run()称为线程体，它包含了要执行的这个线程的内容，Run方法运行结束，此线程终止。然后CPU再调度其他线程。

run()方法是在本线程里的，只是线程里的一个函数，而不是多线程的。如果直接调用run()，其实就相当于调用一个普通的函数而已，直接调用run()方法必须等待run()方法执行完毕才能执行下面的代码，所以执行路径还是只有一条，根本就没有线程的特征，所以在多线程执行时要使用start()方法而不是run()方法。

#### 12. 创建线程池有哪几种方式？

①、newFixedThreadPool(int nThreads)

创建固定长度的线程池，每当提交一个任务就创建一个线程，直到达到线程池的最大数量，这时线程规模将不再变化，当线程发生未预期的错误而结束时，线程池会补充一个新的线程。

特点：

- 核心线程数==最大线程数（没有救急线程被创建），因此也无需超时时间；
- 阻塞队列是无界的，可以放任意数量的任务

> 适合任务量已知，相对耗时的任务

②、newCachedThreadPool()

创建一个可缓存的线程池，如果线程池的规模超过了处理需求，将自动回收空闲线程，而当需求增加时，则可以添加新线程，线程池的规模不存在任何限制。

特点：

- 核心线程数是0，最大线程数是Integer.MAX_VALUE，救急线程的空闲生存时间是60s，意味着：
  - 全是救急线程（60s可以回收）
  - 救急线程可以无限创建
- 队列采用了SynchronousQueue实现，特点是：
  - 它没有容量，没有线程来取是放不进去的（一手交钱，一首交货）

> 整个线程池表现为线程数会根据任务量不断增长，没有上限，当任务执行完毕后，空闲1分钟后释放线程。适合任务数比较密集，但每个任务执行时间较短的情况。

③、newSingleThreadExecutor()

这是一个单线程的Exectuor，它创建单个工作线程来执行任务，如果这个线程异常结束，会创建一个新的来替代它；它的特点是能够确保依照任务在队列中的顺序来串行执行。

④、newScheduledThreadPool(int corePoolSize)

创建一个固定长度的线程池，而且以延迟或定时的方式来执行任务，类似于Timer。

#### 13. 线程池都有哪些状态？

线程池有5种状态：

Running：运行中

ShutDown：不会接受新任务，但会处理阻塞队列剩余任务；

Stop：会中断正在执行的任务，会抛弃阻塞队列任务；

Tidying：任务全执行完毕，活动线程为0即将进入终结状态

Terminated：终结状态

线程各个状态切换框架图：

<img src="C:\Users\cuimaolin\AppData\Roaming\Typora\typora-user-images\image-20210111171730438.png" alt="image-20210111171730438" style="zoom:67%;" />

#### 14. 线程池种 submit()和execute()方法有什么区别？

两个方法都可以向线程池提交任务，execute()方法的返回类型是void，它定义在Executor接口中。

而submit()方法返回持有计算结果的Future对象，它定义在ExecutorService接口中，它扩展了Executor接口，其他线程池像ThreadPoolExecutor和ScheduledThreadPoolExector都有这些方法。

#### 15. 在Java程序中怎么保证多线程的运行安全？

线程安全在三个方面体现：

原子性：提供互斥访问，同一时刻只能能有一个线程对数据进行操作(atomic, synchronized)；

可见性：一个线程对主内存的修改可以及时地被其他线程看到(synchronized, volatile)；

有序性：一个线程观察其他线程中的指令执行顺序，由于指令重排序，该观察结果一般杂乱无序(happens-before原则)

>  happens-before规定了对共享变量的写操作对其他线程的读操作可见，它是可见性与有序性的一套规则总结，抛开以下happens-before规则，JMM并不能保证一个线程对共享变量的写，对于其它线程对该共享变量的读可见。

#### 16. 多线程锁的升级原理是什么？

在Java种，锁共有4种状态，级别从低到高依次未：无状态锁，偏向锁，轻量级锁和重量级锁状态，这几个状态会随着竞争情况逐渐升级。锁可以升级但不能降级。

锁升级的图示过程：

<img src="C:\Users\cuimaolin\AppData\Roaming\Typora\typora-user-images\image-20210105210549671.png" alt="image-20210105210549671" style="zoom:80%;" />

#### 17. 死锁与活锁的区别，死锁与饥饿的区别？

死锁：指两个或两个以上的进程（或线程）在执行过程中，因争夺资源而造成的一种互相等待的现象，若无外力作用，它们将无法推进下去。

活锁：任务或者执行者没有被阻塞，由于某些条件没有满足，导致一致重复尝试，失败，尝试，失败。

死锁和活锁的区别在于，处于死锁的实体是在不断地改变状态，所谓的”活“，而处于死锁的是实体表现为等待；活锁有可能自行解开，死锁则不可能。

饥饿：一个或者多个线程因为种种原因无法获得所需要的资源，导致一直无法执行的状态。

Java中导致饥饿的原因：

- 高优先级线程吞噬所有的低优先级线程的CPU时间；
- 线程被永远阻塞在一个等待进入同步块的状态，因为其他线程总是能在它之前持续地对该同步块进行访问；
- 线程在等待一个本身也处于永久等待完成的对象（比如调用这个对象的wait方法），因为其他线程总是被持续地获得唤醒。

#### 18. 怎么防止死锁？

死锁的四个必要条件：

互斥条件：进程对所分配到的资源不允许其他进程进行访问，若其他进程访问该资源，只能等待，直至占有该资源的进程使用完成释放该资源

请求和保持条件：进程获得一定的资源之后，又对其他资源发出请求，但是该资源可能被其他进程占有，此时请求阻塞，但又对自己活的的资源保持不妨；

不可剥夺条件：是指进程已获得的资源，在未完成使用之前，不可被剥夺，之恶能在使用完后自己释放；

环路等待条件：是指进程发生死锁后，若干进程之间形成一种头尾相接的循环等待资源关系。

这四个条件是死锁的必要条件，只要系统发生死锁，这些条件必然成立，而只要上诉条件之一不满足，就不会发生死锁。

#### 19. ThreadLocal是什么？有哪些使用场景？

线程局部变量是局限于线程内部的变量，属于线程自身所有，不在多个线程间共享。Java提供ThreadLocal类来支持局部变量，是一种实现线程安全的方式。但是在管理环境下（如Web服务器）使用线程局部变量时候要特别小心，在这种情况下，工作线程的声明周期比任何应用变量的生命周期都要长。任何线程局部变量一旦在工作完成后没有释放，Java应用就存在内存泄漏的风险。

#### 20. synchronized底层实现原理

synchronized可以保证方法或者代码块在运行时，同一时刻只有一个方法可以进入到临界区，同时它还可以保证共享变量的内存可见性。

Java代码：

```java
synchronized (lock) {
	counter++;
}
```

对应的字节码：

```java
0: getstatic #2 			// <- lock引用 （synchronized开始）
3: dup
4: astore_1 				// lock引用 -> slot 1
5: monitorenter 			// 将 lock对象 MarkWord 置为 Monitor 指针
6: getstatic #3 			// <- i
9: iconst_1 				// 准备常数 1
10: iadd 					// +1
11: putstatic #3 			// -> i
14: aload_1 				// <- lock引用
15: monitorexit 			// 将 lock对象 MarkWord 重置, 唤醒 EntryList
16: goto 24
19: astore_2 				// e -> slot 2
20: aload_1 				// <- lock引用
21: monitorexit 			// 将 lock对象 MarkWord 重置, 唤醒 EntryList
22: aload_2 				// <- slot 2 (e)
23: athrow 					// throw e
24: return
```

Java中每个对象都可以作为锁，这是synchronized实现同步的基础：

普通同步方法，锁是当前实例对象；

静态同步方法，所是当前类的class对象；

同步方法块，锁是括号里面的对象。

#### 21. synchronized和volatile的区别是什么？

区别：

- volatile本质是在告诉JVM当前变量在寄存器（工作内存）中的值是不确定的，需要从主存中读取； synchronized则是锁定当前变量，只有当前线程可以访问该变量，其他线程被阻塞住；

- volatile仅能使用在变量级别；synchronized则可以使用在变量、方法和类级别的；

- volatile仅能实现变量的修改可见性，不能保证原子性；而synchronized则可以保证变量的修改可见性和原子性；

- volatile不会造成线程的阻塞；synchronized可能会造成线程的阻塞；

- volatile标记的变量不会被编译器优化；synchronized标记的变量可以被编译器优化。

volatile如何保证可见性：

- 写屏障(sfence)保证在该屏障之前的，对共享变量的改动，都同步到主存当中；
- 而读屏障(Ifence)保证在该屏障之后，对共享变量的读取，加载的是主存中最新数据。

volatile如何保证有序性：

- 写屏障会确保指令重排序时，不会将写屏障之前的代码排在写屏障之后；
- 读屏障会确保指令重排序时，不会将读屏障之后的代码排在读屏障之前。

volatile无法解决指令交错：

- 写屏障仅仅是保证之后的读能够读到最新的结果，但不能保证读跑到它前面去；
- 而有序性的保证也只是保证了本线程相关代码不被重排序。

#### 22. 为什么代码会重排序

在执行程序时，为了提供性能，处理器和编译器常常对指令进行重排序，但是不能随意重排序，它必须满足以下两个条件：

- 在单线程环境下不能改变程序运行的结果；
- 存在数据依赖关系的不需要重排序。

需要注意的是：重排序不会影响单线程环境的执行结果，但是会破坏多线程的执行语义。

#### 23. synchronized和Lock有什么区别？

区别：

- 首先synchronized是Java内置关键字，在jvm层面，Lock是个Java类；

- synchronized无法判断是否获取锁的状态，Lock可以判断是否获取到锁；

- synchronized会自动释放锁(a线程执行完同步代码会释放锁；b线程执行过程中发生异常会释放锁)，Lock需在finally中手工释放锁(unlock()方法释放锁)，否则容易造成线程死锁；

- 用synchronized关键字的两个线程1和线程2，如果当前线程1获得锁，线程2等待。如果线程1等待，线程2则会一致等待下去，而Lock锁就不一定会等待下去，如果尝试获取不到锁，线程可以不用一直等待就结束了；

- synchronized的锁可重入、不可中断、非公平，而Lock锁可重入、可判断、可公平(两者即可)；

- Lock锁适合大量同步的代码，synchronized锁适合代码少量的同步问题。

可重入：同一个线程如果首次获得了这把锁，那是因为它是这把锁的拥有者，因此有权利再次获取这把锁。如果是不可重入锁，那么第二次获得锁时，自己也会被锁住。

#### 24. synchronized和 ReentrantLock区别是什么？

synchronized是和if、else、for、while一样的关键字，ReentrantLock是类，这是二者的本质区别。既然ReentrantLock是类，那么它就提供了比synchronized更多更灵活的特性，可以被继承、可以有方法、可以有各种各样的类变量，ReentrantLock比synchronized的扩展性体现在几个点上：

- ReentrantLock可以对获取锁的等待时间进行设置，这样就避免了死锁；
- ReentrantLock可以获取各种锁的信息；
- ReentrantLock可以灵活地实现多路通知；

另外，两者的锁机制其实也是不一样的：ReentrantLock底层调用的是Unsafe的park方法加锁，synchronized操作的应该是对象头中的markwork。

#### 25. 说一下atomic的原理？

Atomic包中的类基本的特性就是在多线程环境下，当有多个线程同时对单个（基本类型及引用类型）变量进行操作时，具有排他性，即当多个线程同时对改变了的值进行更新时，仅有一个线程能够成功，而未成功的线程可以向自旋锁一样继续尝试，一致等到执行成功。

Atomic系列的类中的核心方法都会调用unsafe类的几个本地方法。我们首先需要知道一个东西是Unsafe类，全名为：sun.misc.Unsafe，这个类包含了大量对C代码的操作，包括很多直接内存分配以及原子操作的调用，而它之所以标记为非安全的，是告诉你在这里面大量的方法调用都会存在安全隐患，需要小心使用，否则会导致严重的后果，利用在通过unsafe分配内存的时候，如果自己指定某些区域可能会导致一些类似C++一样的指针越界到其他进程的问题。

与volatile的区别：

- Volatile变量可以确保先行关系，即写操作会发生在读操作之前，但它不能保证原子性。例如用volatile修饰count变量，那么count++操作就不是原子性的。
- 而AtomicInteger类提供的atomic方法可以让这种操作具有原子性，如getAndIncrement()方法会原子性地进行增量操作把当前值加1，其它数据类型和引用变量也可以进行相似操作。

CAS思想：compareAndSet 在set之前，先比较prev与之前值：

- 不一致了，next作废，返回false表示失败；
- 一致，以next设置为新值，返回true表示成功。

> CAS的底层是`lock cmpxchg`指令（x86架构），在单核CPU和多核CPU下窦娥能够保证【比较-交换】的原子性。
>
> 在多核状态下，当某个核执行到带lock的指令是，CPU会让总线锁住，当这个核把此指令执行完毕，再开启总线。这个过程中不会被线程的调度机制所打断，保证了多个线程对内存操作的准确性，是原子的。

