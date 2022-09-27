package commands;

import checkCorrectInput.CheckCorrectData;
import util.Request;

public class RemoveByID implements Command {
    @Override
    public Request getRequest(String arg, boolean fromFile) {
        Request request = new Request();
        CheckCorrectData check = new CheckCorrectData();
        if (check.checkID(arg) == 1) {
            request.setArg(arg);
            return request;
        }
        return null;
    }
}