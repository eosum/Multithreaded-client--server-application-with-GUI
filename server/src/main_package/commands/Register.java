package main_package.commands;

import main_package.app.Server;
import main_package.data.HumanBeing;
import main_package.database.UserExist;
import main_package.util.Response;
import main_package.util.UserInfo;

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
