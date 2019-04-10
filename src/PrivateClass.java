/**
 * @Author: wangzh
 * @Date: 2019/4/10 0010 9:39
 */
public class PrivateClass {
    private PrivateClass(){
        System.out.println("私有构造方法");
        throw new RuntimeException();
    }
    private String string;
    public static void isEmpty(String string){
        if(string == null || "".equals(string)) {
            System.out.println("空");
        }
        else{
            System.out.println("isEmpty"+string);
        }
    }
}
