package commands;

import input.ElementInput;
import checkCorrectInput.CheckCorrectData;
import util.Request;

public class Update implements Command {
    @Override
    public Request getRequest(String arg) {
        Request request = new Request();
        CheckCorrectData check = new CheckCorrectData();

        if (check.checkID(arg) == 1) {
            request.setArg(arg);
            ElementInput element = new ElementInput();
            request.setObject(element.resultElement(0L));
            return request;
        }

        return null;
    }
}