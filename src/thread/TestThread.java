package thread;

/**
 * @Author: wangzh
 * @Date: 2019/4/11 0011 10:32
 */
public class TestThread{
    public static void main(String args[]){
        Thread_Runnable thread_runnable1 = new Thread_Runnable("t1");
        thread_runnable1.start();
        Thread_Runnable thread_runnable2 = new Thread_Runnable("t2");
        thread_runnable2.start();

        Thread_Thread thread_thread1 = new Thread_Thread("t1");
        thread_thread1.start();
        Thread_Thread thread_thread2 = new Thread_Thread("t2");
        thread_thread2.start();


    }
}
class Thread_Runnable implements Runnable{
    private Thread thread;
    private String threadName;
    static private int number = 4;

    public Thread_Runnable(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running: " + threadName);
        try{
            for(int i = 0;i < Thread_Runnable.number;i++){
                System.out.println("Running: " + threadName + "," + i);
                Thread.sleep(50);
            }
        }catch(InterruptedException e){
            System.out.println("Thread " + threadName + " interrupted");
        }
        System.out.println("Thread " + threadName + " exiting");
    }
    public void start(){
        System.out.println("Starting " + threadName);
        if(thread == null){
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}
class Thread_Thread extends Thread{

    private String threadName;
    private static int number = 5;


    public Thread_Thread(String threadName){
        this.threadName = threadName;
    }
    @Override
    public void run(){
        for(int i = 0;i < Thread_Thread.number;i++){
            System.out.println("Starting: " + threadName + " " + i);
            try{
                sleep(50);
            }catch (InterruptedException e){

                System.out.print("Thread: " +threadName + " interrupted");
            }
        }
    }
}
