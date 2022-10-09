package packages.util;

public class UserInfo {
    private String user;
    private String password;
    public UserInfo(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
