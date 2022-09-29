package commands;

import app.ServerProvider;
import data.HumanBeing;
import util.DataForSending;
import util.DataProcessing;
import util.Request;
import util.SenderResult;

public class AddIfMin implements Command {
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