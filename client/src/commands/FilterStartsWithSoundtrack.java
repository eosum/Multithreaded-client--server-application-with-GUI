package commands;

import app.ServerProvider;
import util.DataForSending;
import util.Request;
import util.SenderResult;

public class FilterStartsWithSoundtrack implements Command {
    ServerProvider serverProvider = ServerProvider.getServerProvider();
    @Override
    public SenderResult getRequest(DataForSending object, Request request) {
        request.setArg(object.getSoundtrackName());
        return serverProvider.send(request);
    }
}