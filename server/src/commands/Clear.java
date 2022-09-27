package commands;

import app.*;
import data.HumanBeing;
import util.Response;
import util.UserInfo;

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
        if(!result) {
            response.setMessage("Коллекция не очищена");
        }
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "clear - отчистить коллекцию";
    }
}
