package thread;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 统计目录下包含关键字keyword的文件数量
 * @Author: wangzh
 * @Date: 2019/4/13 0013 12:51
 */
public class TestFuture {
    public static File root_directory = new File("E:\\study\\test");
    public static String keyword = "hello";
    public static void main(String[] args){
        MatchCounter matchCounter = new MatchCounter(root_directory, keyword);
        FutureTask<Integer> futureTask = new FutureTask<>(matchCounter);
        new Thread(futureTask).start();
        try{
            System.out.println(futureTask.get());
        }catch(InterruptedException e){
        }catch(ExecutionException e1){
            e1.printStackTrace();
        }

    }
}
class MatchCounter implements Callable<Integer>{
    private File directory;
    private String keyword;

    public MatchCounter(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        try{
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();

            for(File file : files){
                if(file.isDirectory()){
                    MatchCounter matchCounter = new MatchCounter(file, keyword);
                    FutureTask<Integer> futureTask = new FutureTask<>(matchCounter);
                    results.add(futureTask);
                    new Thread(futureTask).start();
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
