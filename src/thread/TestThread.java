package thread;

/**
 * @Author: wangzh
 * @Date: 2019/4/11 0011 10:32
 */
public class TestThread{
    public static void main(String args[]){
//        Thread_Runnable thread_runnable1 = new Thread_Runnable("t1");
//        thread_runnable1.start();
//        Thread_Runnable thread_runnable2 = new Thread_Runnable("t2");
//        thread_runnable2.start();

        Thread_Thread thread_thread1 = new Thread_Thread("t1");
        thread_thread1.start();
        Thread_Thread thread_thread2 = new Thread_Thread("t2");
        thread_thread2.start();


    }

}