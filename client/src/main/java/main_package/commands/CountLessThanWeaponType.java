package main_package.commands;

import main_package.serverConnection.ServerProvider;
import main_package.util.DataForSending;
import main_package.util.Request;
import main_package.util.SenderResult;
import main_package.validation.DataValidation;

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