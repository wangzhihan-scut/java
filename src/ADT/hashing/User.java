package ADT.hashing;

/**
 * @Author: wangzh
 * @Date: 2019/5/13 0013 10:40
 */
public class User {
    private String username;
    private String password;

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && username.equals(((User) obj).username);
    }
}
