package commands;

import util.Request;

public class FilterStartsWithSoundtrack implements Command {
    @Override
    public Request getRequest(final String arg) {
        final Request request = new Request();
        request.setArg(arg);
        return request;
    }
}