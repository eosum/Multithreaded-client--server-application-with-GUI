package commands;

import util.Request;

public class Clear implements Command{

    @Override
    public Request getRequest(String arg) {
        Request request = new Request();
        return request;
    }

}