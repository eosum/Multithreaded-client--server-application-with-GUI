package packages.commands;

import packages.app.SceneSwitch;
import packages.connection.ServerProvider;
import packages.util.DataForSending;
import packages.util.Request;
import packages.util.SenderResult;
import packages.validation.DataValidation;

public class CountLessThanWeaponType implements Command {
    DataValidation validator = new DataValidation();
    ServerProvider serverProvider = ServerProvider.getServerProvider();
    @Override
    public SenderResult getRequest(DataForSending object, Request request) {
        if(!validator.validateWeaponType(object.getWeaponType())) {
            return new SenderResult(false, SceneSwitch.getResourceBundle().getString("inputError"));
        }
        request.setArg(object.getWeaponType());
        return serverProvider.send(request);
    }
}