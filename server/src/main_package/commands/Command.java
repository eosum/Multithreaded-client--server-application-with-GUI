package main_package.commands;

import main_package.data.HumanBeing;
import main_package.util.Response;
import main_package.util.UserInfo;

public interface Command {
    void execute(String args, HumanBeing object, UserInfo userInfo, Response response);

}
