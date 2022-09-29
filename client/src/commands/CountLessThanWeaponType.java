package commands;

import app.ServerProvider;
import util.DataForSending;
import util.Request;
import util.SenderResult;
import validation.DataValidation;

public class CountLessThanWeaponType implements Command {
    DataValidation validator = new DataValidation();
    ServerProvider serverProvider = ServerProvider.getServerProvider();
    @Override
    public SenderResult getRequest(DataForSending object, Request request) {
        if(!validator.validateWeaponType(object.getWeaponType())) {
            return new SenderResult(false, "Недопустимое значение в Weapon Type");
        }
        request.setArg(object.getWeaponType());
        return serverProvider.send(request);
    }
}