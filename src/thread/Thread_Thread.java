package thread;

/**
 * @Author: wangzh
 * @Date: 2019/4/11 0011 10:37
 */
public class Thread_Thread extends Thread{

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
