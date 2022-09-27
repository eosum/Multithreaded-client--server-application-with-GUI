package commands;

import app.CollectionManager;
import app.Server;
import data.HumanBeing;
import util.Response;
import util.UserInfo;

/**
 * AddIfMin class
 */
public class AddIfMin implements Command{
    private final CollectionManager collectionManager;

    public AddIfMin(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Add an element if it is smaller than the smallest existing one
     *
     * @param args     arguments for command
     * @param object
     * @param response
     */
    @Override
    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        if(!collectionManager.addIfMin(object, userInfo.getUser())) {
            response.setMessage("Элемент не добавлен");
        }
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "add_if_min - добавляет элемент, если он меньше чем наименьший элемент коллекции";
    }

}
