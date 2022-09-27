package commands;

import input.ElementInput;
import util.Request;

public class AddIfMin implements Command {
    public Request getRequest(String arg, boolean fromFile) {
        Request request = new Request();
        ElementInput element = new ElementInput();
        request.setObject(element.resultElement(0L));
        return request;
    }
}