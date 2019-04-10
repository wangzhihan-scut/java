import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: wangzh
 * @Date: 2019/4/10 0010 14:52
 */
public class SHA256Util {
    private SHA256Util(){
        throw new RuntimeException();
    }

    /**
     *
     * @param plainText 明文
     * @return cipherText  返回加密后的密文
     */
    public static String SHA256(String plainText){
        MessageDigest messageDigest;
        String cipherText = plainText;
        try{
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = plainText.getBytes("utf-8");
            messageDigest.update(bytes);
            cipherText = byte2Hex(messageDigest.digest());

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch(UnsupportedEncodingException e1){
            e1.printStackTrace();
        }
        return cipherText;
    }
    public static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
    /**
     * 校验明文与密文是否匹配
     *
     * @param plainText  明文
     * @param cipherText 密文
     * @return 匹配结果
     */
    public static boolean verify(String plainText, String cipherText) {
        return plainText != null && SHA256Util.SHA256(plainText).equals(cipherText);
    }
}
