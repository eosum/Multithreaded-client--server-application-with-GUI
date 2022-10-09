package packages.commands;

import packages.app.*;
import packages.data.HumanBeing;
import packages.util.Response;
import packages.util.UserInfo;

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
