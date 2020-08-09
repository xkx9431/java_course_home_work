package home_work.week04.userMsg;


import java.io.Serializable;

/**
 * User 类的特征有：
 * userName --用户名,
 * userId -- 密码(字符串类型)
 */

public class User implements Serializable {
    private String userName;
    private  String password;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User[" +
                "userName='" + userName + '\'' +
                ", Password='" + password + '\'' +
                ']';
    }
}
