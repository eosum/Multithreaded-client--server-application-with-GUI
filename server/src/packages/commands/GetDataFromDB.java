package packages.commands;

import packages.app.CollectionManager;
import packages.app.Server;
import packages.data.HumanBeing;
import packages.util.Response;
import packages.util.UserInfo;

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
