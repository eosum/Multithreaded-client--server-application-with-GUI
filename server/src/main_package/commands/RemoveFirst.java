package main_package.commands;

import main_package.app.*;
import main_package.data.HumanBeing;
import main_package.util.Response;
import main_package.util.UserInfo;

/**
 * RemoveFirst class
 */
public class RemoveFirst implements Command {
    private final CollectionManager collectionManager;

    public RemoveFirst(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        response.setSuccess(collectionManager.removeFirst(userInfo.getUser()));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "remove_first - удаляет первый элемент в коллекции";
    }
}
