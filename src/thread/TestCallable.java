package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Author: wangzh
 * @Date: 2019/4/13 0013 12:05
 */
public class TestCallable implements Callable<Integer>{
    private static int number = 0;
    @Override
    public Integer call() throws Exception {
        return ++number;
    }
    public static void main(String[] args){
        int i = 0;
        try{
            TestCallable testCallable = new TestCallable();
            FutureTask<Integer> futureTask = new FutureTask<>(testCallable);
            System.out.println("1: "+futureTask.isDone());
            (new Thread(futureTask)).start();
            System.out.println("2: "+futureTask.isDone());
            i = futureTask.get();
            System.out.println("3: "+futureTask.isDone());
        }catch (Exception e){

        }
        System.out.println(i);
    }
}
