package main_package.commands;

import main_package.serverConnection.ServerProvider;
import main_package.util.DataForSending;
import main_package.util.Request;
import main_package.util.SenderResult;

public class Clear implements Command{
    ServerProvider serverProvider = ServerProvider.getServerProvider();
    @Override
    public SenderResult getRequest(DataForSending object, Request request) {
        return serverProvider.send(request);
    }

}