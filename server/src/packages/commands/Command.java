package packages.commands;

import packages.data.HumanBeing;
import packages.util.Response;
import packages.util.UserInfo;

public interface Command {
    void execute(String args, HumanBeing object, UserInfo userInfo, Response response);

}
