package thread;

/**
 * @Author: wangzh
 * @Date: 2019/4/11 0011 10:59
 */
public class TestRunnable {
    public static void main(String[] args) {
        Runner1 runner1 = new Runner1();
//        Runner1 runner11 = new Runner1();
//        runner1.run();
//        runner11.run();
        Runner2 runner2 = new Runner2();
//      Thread(Runnable target) 分配新的 Thread 对象。
        Thread thread1 = new Thread(runner1);
        Thread thread2 = new Thread(runner2);
        System.out.print(Thread.currentThread().getThreadGroup());

        //start方法调用后，线程处于可执行态(Runnable);  两个线程执行顺序随机，由操作系统决定
        thread1.start();
        thread2.start();
//        thread1.start();
        //直接调用run
        thread1.run();
        thread2.run();
    }
}

/**
 * 实现了Runnable接口，jdk就知道这个类是一个线程
 */
class Runner1 implements Runnable {
    private static int number = 100;

    @Override
    /**
     * run方法为线程执行体
     */
    public void run() {
        System.out.print(Thread.currentThread());
        for (int i = 0; i < Runner1.number; i++) {
            System.out.println("进入Runner1运行状态——————————" + i);
        }
    }
}

/**
 * 实现了Runnable接口，jdk就知道这个类是一个线程
 */
class Runner2 implements Runnable {
    private static int number = 100;

    @Override
    public void run() {
        System.out.print(Thread.currentThread());
        for (int i = 0; i < Runner2.number; i++) {
            System.out.println("进入Runner2运行状态==========" + i);
        }
    }
}
