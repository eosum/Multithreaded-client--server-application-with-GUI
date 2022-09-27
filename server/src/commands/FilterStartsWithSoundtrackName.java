package commands;

import app.*;
import data.HumanBeing;
import util.Response;
import util.UserInfo;

/**
 * FilterStartsWithSoundtrackName class
 */
public class FilterStartsWithSoundtrackName implements Command {
    private final CollectionManager collectionManager;

    public FilterStartsWithSoundtrackName(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * @param args     contains the substring to find
     * @param object
     * @param response
     */
    @Override
    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        String SubString = String.join(" ", args);
        response.setObject(collectionManager.filterStartsWithSoundtrackName(SubString));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "filter_starts_with_soundtrack_name - выводит элементы, имя песни которых начинается с заданной подстроки";
    }

}
