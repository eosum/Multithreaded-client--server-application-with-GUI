package main_package.commands;

import main_package.app.ServerProvider;
import main_package.util.DataForSending;
import main_package.util.Request;
import main_package.util.SenderResult;

public class RemoveByID implements Command {
    ServerProvider serverProvider = ServerProvider.getServerProvider();
    @Override
    public SenderResult getRequest(DataForSending object, Request request) {
        request.setArg(object.getId());
        return serverProvider.send(request);
    }
}