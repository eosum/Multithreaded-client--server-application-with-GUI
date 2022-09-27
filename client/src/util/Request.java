package util;
import data.HumanBeing;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String command = null;
    private String arg;
    private String user;
    private String password;
    private HumanBeing object;

    public String getCommand() {
        return command;
    }
    public String getArg() {
        return arg;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    public void setCommand(String command) {
        this.command = command;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public void setObject(HumanBeing object) {
        this.object = object;
    }


}
