package thread;

/**
 * @Author: wangzh
 * @Date: 2019/4/11 0011 10:15
 */
public class Thread_Runnable implements Runnable{
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

