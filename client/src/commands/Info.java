package commands;

import util.Request;

public class Info implements Command{

    @Override
    public Request getRequest(String arg, boolean fromFile) {
        Request request = new Request();
        return request;
    }

}