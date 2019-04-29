package jvm.java_gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Date;

/**
 * @Author: wangzh
 * @Date: 2019/4/29 0029 10:14
 */
public class ReferenceCountingGC extends Date{
    private String name;
    public Object ref = null;

    public ReferenceCountingGC(String s){
        name = s;
    }
    private static final int _1MB = 1024*1024;

    private byte[] bigSize = new byte[1 * _1MB];

    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        System.out.println("垃圾回收" + this.getTime() +"_"+ name);
    }
    public static void main(String[] args){
        ReferenceCountingGC objA = new ReferenceCountingGC("A");
        ReferenceCountingGC objB = new ReferenceCountingGC("B");
        //直接引用自己？？？
        objA.ref = objA;
        objB.ref = objB;
        objA = null;
        objB = null;
        //弱引用将被清理
        WeakReference weakReference = new WeakReference(new ReferenceCountingGC("weak"));

        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference phantomReference = new PhantomReference(new ReferenceCountingGC("queue"), queue);
        System.gc();
        System.out.println("=================");
    }
}
