package cn.jims.entity;

/**
 * 实体类
 * Created by Jims on 2017/1/17.
 */
public class User {
    private String username;
    private String password;
    //确认密码
    private String rePassword;
    private String verifyCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rePassword='" + rePassword + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
