package main_package.commands;

import main_package.app.*;
import main_package.data.HumanBeing;
import main_package.util.Response;
import main_package.util.UserInfo;

/**
 * Clear class
 */
public class Clear implements Command {

    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Clear existing collection
     *
     * @param args     arguments for command
     * @param object
     * @param response
     */
    @Override
    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        boolean result = collectionManager.clear(userInfo.getUser());
        response.setSuccess(result);
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "clear - отчистить коллекцию";
    }
}
