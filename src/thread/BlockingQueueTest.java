package thread;

import sun.security.provider.NativePRNG;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: wangzh
 * @Date: 2019/4/11 0011 16:11
 */
public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 15;
    /**
     * dummy虚拟文件，因为只有一个生产者，防止线程获取不到文件无限阻塞下去
     */
    private static final File Dummy = new File("");
    /**
     * 共享队列，当队列为空或者为满时会阻塞当前线程（生产者或者消费者）
     */
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args){
        try(Scanner in = new Scanner(System.in)){
            System.out.println("Enter directory:（like E:\\study\\test）");
            String directory = in.nextLine();
            System.out.println("Enter keyword:（like 'hello world'）");
            String keyword = in.nextLine();

            //lambda表达式创建Runnable
            Runnable enumerator = () -> {
                try{
                    File tmp = new File(directory);
                    enumerate(tmp);
                    System.out.println("dummy文件进入队列最末尾："+queue.size());
                    queue.put(Dummy);
                }catch(InterruptedException e){
                }
            };
            new Thread(enumerator).start();

            for(int i = 1;i <= SEARCH_THREADS;i++){
                System.out.println("==========="+i+"===========");
                Runnable searcher = () -> {
                    try{
                        boolean done = false;
                        while(!done){
                            File file = queue.take();
                            if(file.equals(Dummy)){
                                //意味着生产者生产的文件均已消费完
                                done = true;
                                queue.put(Dummy);
                                System.out.println("队列已处理完，本线程获得dummy");
                            }else{
                                search(file, keyword);
                            }
                        }
                    }catch (InterruptedException e){
                    }catch (IOException e1){
                        e1.printStackTrace();
                    }

                };
                new Thread(searcher).start();
                System.out.println("==========="+i+"===========");
            }
        }
    }
    /**
     * 递归地enumerate给定文件夹以及其子目录
     * @param directory 给定文件夹
     * @throws InterruptedException
     */
    public static void enumerate(File directory) throws  InterruptedException{
        File [] files = directory.listFiles();
        for (File file : files){
            if(file.isDirectory()){
                enumerate(file);
            }
            else{
                queue.put(file);
            }
        }
        System.out.println("队列长度："+queue.size());
    }
    public static void search(File file, String keyword) throws IOException{
        try (Scanner in = new Scanner(file, "UTF-8")) {
            int lineNumber = 0;
            while(in.hasNextLine()){
                lineNumber++;
                String line = in.nextLine();
                if(line.contains(keyword)) {
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
                }
            }
        }
    }
}
