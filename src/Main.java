import util.SHA256Util;

import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String s = "";
        if(s.equals(null)){
            System.out.println("一样");
        }
        else{
            System.out.println("不一样");
        }
        PrivateClass.isEmpty(s);
        s = "18051294201@13.com";
        if(Pattern.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",s)){
            System.out.println("匹配");
        }
        else{
            System.out.println("不匹配");
        }

        String input = "xiaohaitun";
        String encrpt = SHA256Util.SHA256(input);
        System.out.println(encrpt);

        String s1 = String.format("http://www.i-test.com.cn/user/activate?token=%s", "eyJ1c2VyUGFzc3dvcmQiOiIxMjM0NTYiLCJ1c2VyTmFtZSI6InhpYW9oYWl0dW4iLCJlbWFpbCI6IjEyMzQ1NkBxcS5jb20ifQ");
        String s2 = "http://www.i-test.com.cn/user/activate?token=" + "eyJ1c2VyUGFzc3dvcmQiOiIxMjM0NTYiLCJ1c2VyTmFtZSI6InhpYW9oYWl0dW4iLCJlbWFpbCI6IjEyMzQ1NkBxcS5jb20ifQ";
        System.out.println(s1);
        System.out.println(s2);
        if(s1.equals(s2)){
            System.out.println("相等");
        }
    }
}
