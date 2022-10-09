package packages.commands;

import packages.app.CollectionManager;
import packages.app.Server;
import packages.data.HumanBeing;
import packages.util.Response;
import packages.util.UserInfo;

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
            response.setSuccess(false);
        }
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "add - добавляет новый элемент в коллекцию";
    }
}
