import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试堆溢出
 * VM args: -Xms3m -Xmx3m -XX:+HeapDumpOnOutOfMemoryError
 * @Author: wangzh
 * @Date: 2019/4/12 0012 19:46
 */
public class HeapOOM {
    public static void main(String[] args){
        class OOMObject{
            String string;
            OOMObject(String s){
                string = s;
            }
        }
        List<OOMObject> list = new ArrayList<>();
        int i = 0;
        while(true){
            i++;
            System.out.println("最大"+Runtime.getRuntime().maxMemory()/1024/1024);
            System.out.println("空闲"+Runtime.getRuntime().freeMemory()/1024/1024);
            System.out.println("总计"+Runtime.getRuntime().totalMemory()/1024/1024);
            i++;
            //无限创建对象并使用集合保存其引用使其无法被GC回收
            list.add(new OOMObject("hello"));
        }
//        System.out.println(list.size());
    }
}
