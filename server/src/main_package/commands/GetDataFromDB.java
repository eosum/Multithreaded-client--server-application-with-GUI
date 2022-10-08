package main_package.commands;

import main_package.app.CollectionManager;
import main_package.app.Server;
import main_package.data.HumanBeing;
import main_package.util.Response;
import main_package.util.UserInfo;

public class GetDataFromDB implements Command{
    private final CollectionManager collectionManager;

    public GetDataFromDB(CollectionManager collectionManager) {
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
        response.setObject(collectionManager.getData());
        Server.sendResponse(response, response.getKey());
    }

}
