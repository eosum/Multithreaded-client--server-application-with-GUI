package packages.commands;

import packages.app.Server;
import packages.data.HumanBeing;
import packages.database.UserExist;
import packages.util.Response;
import packages.util.UserInfo;

public class Register implements Command {
    @Override
    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        response.setMessage(UserExist.register(userInfo.getUser(), userInfo.getPassword()));
        System.out.println(response.getMessage());
        if(!response.getMessage().equals("Успешно")) response.setSuccess(false);
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "register - регистрация";
    }
}
