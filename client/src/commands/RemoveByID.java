package commands;

import app.ServerProvider;
import util.DataForSending;
import util.Request;
import util.SenderResult;

public class RemoveByID implements Command {
    ServerProvider serverProvider = ServerProvider.getServerProvider();
    @Override
    public SenderResult getRequest(DataForSending object, Request request) {
        request.setArg(object.getId());
        return serverProvider.send(request);
    }
}