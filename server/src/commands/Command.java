package commands;

import data.HumanBeing;
import util.Response;
import util.UserInfo;

public interface Command {
    void execute(String args, HumanBeing object, UserInfo userInfo, Response response);

}
