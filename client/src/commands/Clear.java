package commands;

import app.ServerProvider;
import util.DataForSending;
import util.Request;
import util.SenderResult;

public class Clear implements Command{
    ServerProvider serverProvider = ServerProvider.getServerProvider();
    @Override
    public SenderResult getRequest(DataForSending object, Request request) {
        return serverProvider.send(request);
    }

}