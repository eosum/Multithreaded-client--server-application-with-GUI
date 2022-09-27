package commands;

import app.CollectionManager;
import app.Server;
import data.HumanBeing;
import util.Response;
import util.UserInfo;

/**
 * RemoveGreater class
 */
public class RemoveGreater implements Command {
    private final CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        response.setMessage(collectionManager.removeGreater(object, userInfo.getUser()));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "remove_greater - удаляет все элементы, превышающий заданный";
    }

}
