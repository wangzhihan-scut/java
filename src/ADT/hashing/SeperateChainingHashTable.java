package ADT.hashing;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 分离链接法解决hash冲突
 * 再散列建立更大的hash表，大小为原来的两倍以上，然后复制原有元素
 * @Author: wangzh
 * @Date: 2019/5/13 0013 10:16
 */
public class SeperateChainingHashTable<AnyType> {

    private static final int DEFAULT_TABLE_SIZE = 101;

    private List<AnyType>[] theLists;
    private int currentSize;

    public SeperateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }
    public SeperateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for(int i = 0;i<theLists.length;i++){
            theLists[i] = new LinkedList<>();
        }
    }

    public void insert(AnyType x){
        if(!contains(x)){
            List<AnyType> whichList = theLists[myhash(x)];
            whichList.add(0, x);
            if(++currentSize > theLists.length){
                rehash();
            }
        }else{
            System.out.println("x already exists");
        }
    }
    public void remove(AnyType x){
        if(contains(x)){
            theLists[myhash(x)].remove(x);
            currentSize--;
        }else{
            System.out.println("x not exists");
        }
    }
    public boolean contains(AnyType x){
        List<AnyType> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }
    public void makeEmpty(){
        for(int i = 0;i<theLists.length;i++){
            theLists[i].clear();
        }
        currentSize = 0;
    }

    private int myhash(AnyType x){
        int hashVal = x.hashCode();

        hashVal += theLists.length;
        hashVal %= theLists.length;

        return hashVal;
    }
    private void rehash(){
        List<AnyType>[] oldLists = theLists;
        theLists = new List[nextPrime(2 * theLists.length)];
        for(int i = 0;i < theLists.length;i++){
            theLists[i] = new LinkedList<>();
        }

        currentSize = 0;
        for(int i = 0;i < oldLists.length;i++){
            for(AnyType item: oldLists[i]){
                insert(item);
            }
        }
    }

    /**
     * 大于n的第一个素数
     * @param n
     * @return
     */
    private static int nextPrime(int n){
        if(n % 2 == 0){
            n++;
        }
        boolean notPrime = true;
        for(;notPrime; n += 2){
            if(isPrime(n)){
                notPrime = false;
            }
        }
        return n;
    }
    private static boolean isPrime(int n){
        if(n < 3){
            return n > 1;
        }
        for(int i = 0;i < Math.sqrt(n);i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        List<Integer> list = new LinkedList<>();
        System.out.println(list instanceof Collections);
    }
}
