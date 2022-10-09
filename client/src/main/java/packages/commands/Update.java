package packages.commands;

import packages.app.SceneSwitch;
import packages.connection.ServerProvider;
import packages.data.HumanBeing;
import packages.util.DataForSending;
import packages.util.DataProcessing;
import packages.util.Request;
import packages.util.SenderResult;

public class Update implements Command {
    ServerProvider serverProvider = ServerProvider.getServerProvider();
    @Override
    public SenderResult getRequest(DataForSending object, Request request) {
        DataProcessing dataProcess = new DataProcessing();
        HumanBeing result = dataProcess.dataProcessing(object);
        if (result == null || result.getId() == -1) {
            return new SenderResult(false, SceneSwitch.getResourceBundle().getString("inputError"));
        }
        request.setObject(result);
        return serverProvider.send(request);
    }
}