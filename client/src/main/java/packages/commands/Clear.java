package packages.commands;

import packages.connection.ServerProvider;
import packages.util.DataForSending;
import packages.util.Request;
import packages.util.SenderResult;

public class Clear implements Command{
    ServerProvider serverProvider = ServerProvider.getServerProvider();
    @Override
    public SenderResult getRequest(DataForSending object, Request request) {
        return serverProvider.send(request);
    }

}