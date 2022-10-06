package main_package.commands;

import main_package.app.*;
import main_package.data.HumanBeing;
import main_package.util.Response;
import main_package.util.UserInfo;

/**
 * RemoveById class
 */
public class RemoveById implements Command {

    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        response.setSuccess(collectionManager.removeById(Long.parseLong(args), userInfo.getUser()));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "remove_by_id - удаляет элемент из коллекции по его id";
    }
}
