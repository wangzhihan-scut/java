package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 线程池,项目中会用到
 * @Author: wangzh
 * @Date: 2019/4/12 0012 11:23
 */
public class TestThreadPool {
    Callable<Integer> callable;
    Future<Integer> s;
    FutureTask<Integer> futureTask;
    public static void main(String[] args){

    }

}
class MatchCount implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {

    }
}
