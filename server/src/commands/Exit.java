package commands;

import data.HumanBeing;
import util.Response;
import util.UserInfo;

/**
 * Exit class
 */
public class Exit implements Command {

    /**
     * Exit the program
     *
     * @param args     arguments
     * @param object
     * @param response
     */
    @Override
    public void execute(String args, HumanBeing object, UserInfo userInfo, Response response) {
        System.exit(0);
    }

    @Override
    public String toString() {
        return "exit - завершение программы";
    }
}
