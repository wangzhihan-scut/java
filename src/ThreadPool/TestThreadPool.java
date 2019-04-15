package ThreadPool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * 线程池,项目中会用到
 * @Author: wangzh
 * @Date: 2019/4/12 0012 11:23
 */
public class TestThreadPool {
    public static File root_directory = new File("E:\\study\\test");
    public static String keyword = "hello";
    public final static int MAX_THREAD_NUM = 10;
    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        MatchCounter matchCounter = new MatchCounter(root_directory, keyword, executorService);
        Future<Integer> future = executorService.submit(matchCounter);
        try{
            System.out.println(future.get());
        }catch(InterruptedException e){
        }catch(ExecutionException e1){
            e1.printStackTrace();
        }
        executorService.shutdown();
        int largestPoolSize = ((ThreadPoolExecutor) executorService).getLargestPoolSize();
        System.out.println("最大线程数目：" + largestPoolSize);
    }
}
class MatchCounter implements Callable<Integer>{
    private File directory;
    private String keyword;
    private ExecutorService pool;

    public MatchCounter(File directory, String keyword, ExecutorService pool) {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        try{
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();

            for(File file : files){
                if(file.isDirectory()){
                    MatchCounter matchCounter = new MatchCounter(file, keyword, pool);
                    Future<Integer> result = pool.submit(matchCounter);
                    results.add(result);
                }else{
                    if(search(file, keyword)){
                        count++;
                    }
                }
            }
            for(int i = 0;i<results.size();i++){
                count += results.get(i).get();
            }
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return count;
    }
    public boolean search(File file, String keyword) throws IOException {
        boolean flag = false;
        try (Scanner in = new Scanner(file, "UTF-8")) {
            while(in.hasNextLine()){
                String line = in.nextLine();
                if(line.contains(keyword)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
}

