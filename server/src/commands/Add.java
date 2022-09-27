package commands;

import app.CollectionManager;
import app.Server;
import data.HumanBeing;
import util.Response;
import util.UserInfo;

/**
 * Command Add class
 */
public class Add implements Command {
    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Add an element to collection
     *
     * @param args   arguments for command
     * @param object
     */
    @Override
    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        if(!collectionManager.add(object, userInfo.getUser())) {
            response.setMessage("Элемент не добавлен");
            response.setSuccess(false);
        }
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "add - добавляет новый элемент в коллекцию";
    }
}
