package commands;

import checkCorrectInput.CheckCorrectData;
import util.Request;

public class CountLessThanWeaponType implements Command {
    @Override
    public Request getRequest(String arg, boolean fromFile) {
        Request request = new Request();
        CheckCorrectData check = new CheckCorrectData();
        if (check.checkWeaponType(arg) == 1) {
            request.setArg(arg);
            return request;
        }
        return null;
    }
}