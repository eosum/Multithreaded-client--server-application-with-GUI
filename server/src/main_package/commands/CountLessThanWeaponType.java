package main_package.commands;

import main_package.app.CollectionManager;
import main_package.app.Server;
import main_package.data.HumanBeing;
import main_package.util.Response;
import main_package.util.UserInfo;

/**
 * CountLessThanWeaponType class
 */
public class CountLessThanWeaponType implements Command {
    private final CollectionManager collectionManager;

    public CountLessThanWeaponType(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Counts the number of element that has smaller weapon type
     *
     * @param args     arguments for command
     * @param object
     * @param response
     */
    @Override
    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        response.setMessage(collectionManager.countLessThanWeaponType(args));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "count_less_than_weapon_type - выводит кол - во элементов, weapon type которых меньше заданного";
    }

}
