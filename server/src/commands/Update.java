package commands;

import app.CollectionManager;
import app.Server;
import data.HumanBeing;
import util.Response;
import util.UserInfo;

/**
 * Update class
 */
public class Update implements Command {
    private final CollectionManager collectionManager;

    public Update(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        response.setMessage(collectionManager.updateById(object, userInfo.getUser()));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "update - обновить значение элемента коллекции, id которого совпадает с исходным";
    }
}
