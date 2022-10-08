package main_package.commands;

import main_package.app.*;
import main_package.data.HumanBeing;
import main_package.util.Response;
import main_package.util.UserInfo;

/**
 * Info class
 */
public class Info implements Command {

    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Prints the collection info
     *
     * @param args     arguments
     * @param object
     * @param response
     */
    @Override
    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        response.setMessage(collectionManager.info());
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "info - выводит информацию о коллекции (тип, дата инициализации и тд)";
    }
}