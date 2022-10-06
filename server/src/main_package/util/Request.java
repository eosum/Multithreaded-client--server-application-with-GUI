package main_package.util;
import main_package.data.HumanBeing;

import java.io.Serializable;
import java.nio.channels.SelectionKey;

public class Request implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String command = null;
    private String arg;
    private String user;
    private String password;
    private HumanBeing object;
    private transient SelectionKey key;

    public void setKey(SelectionKey key) {
        this.key = key;
    }
    public SelectionKey getKey() {
        return key;
    }
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public String getArg() {
        return arg;
    }

    public HumanBeing getObject() {
        return object;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
