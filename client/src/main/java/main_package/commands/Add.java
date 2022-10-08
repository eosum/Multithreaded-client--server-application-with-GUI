package main_package.commands;

import main_package.serverConnection.ServerProvider;
import main_package.data.HumanBeing;
import main_package.util.DataForSending;
import main_package.util.DataProcessing;
import main_package.util.Request;
import main_package.util.SenderResult;

public class Add implements Command {
    ServerProvider serverProvider = ServerProvider.getServerProvider();
    @Override
    public SenderResult getRequest(DataForSending object, Request request) {
        DataProcessing dataProcess = new DataProcessing();
        HumanBeing result = dataProcess.dataProcessing(object);
        if (result == null) {
            return new SenderResult(false, "Проверьте корректность ввода. Также поля не должны быть пустыми");
        }
        request.setObject(result);
        return serverProvider.send(request);
    }
}