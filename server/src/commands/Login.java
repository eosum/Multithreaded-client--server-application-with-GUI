package commands;

import app.CollectionManager;
import app.Server;
import data.HumanBeing;
import database.UserExist;
import util.Response;
import util.UserInfo;

public class Login implements Command {

    @Override
    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        response.setMessage(UserExist.login(userInfo.getUser(), userInfo.getPassword()));
        if(!response.getMessage().equals("Успешно")) response.setSuccess(false);
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "login - вход в приложение";
    }
}
