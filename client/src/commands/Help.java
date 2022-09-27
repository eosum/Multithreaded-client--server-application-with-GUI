package commands;

import util.Request;
public class Help implements Command {
    @Override
    public Request getRequest(String arg, boolean fromFile) {
        Request request = new Request();
        return request;
    }

}