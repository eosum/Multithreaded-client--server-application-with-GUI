package commands;

import input.ElementInput;
import util.Request;

public class Add implements Command {
    public Request getRequest(String arg) {
        Request request = new Request();
        ElementInput element = new ElementInput();
        request.setObject(element.resultElement(0L));
        return request;
    }
}