package main_package.util;

public class UserInfo {
    private String login;
    private String password;

    public UserInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getUserLogin() {
        return login;
    }

    public String getUserPassword() {
        return password;
    }
}
