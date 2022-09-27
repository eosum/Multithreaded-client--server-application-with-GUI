package commands;

import app.*;
import data.HumanBeing;
import util.Response;
import util.UserInfo;

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
        response.setMessage(collectionManager.removeFirst(userInfo.getUser()));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "remove_first - удаляет первый элемент в коллекции";
    }
}
