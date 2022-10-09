package packages.app;

import packages.commands.*;
import packages.util.DataForSending;
import packages.util.Request;
import packages.util.SenderResult;
import packages.util.UserInfo;

import java.util.HashMap;

public class CommandList {
    public final HashMap<String, Command> commands = new HashMap<>();

    public CommandList() {
        commands.put("add", new Add());
        commands.put("update", new Update());
        commands.put("removeById", new RemoveByID());
        commands.put("clear", new Clear());
        commands.put("removeFirst", new RemoveFirst());
        commands.put("countLessThanWeaponType", new CountLessThanWeaponType());
        commands.put("info", new Info());
        commands.put("removeGreater", new RemoveGreater());
        commands.put("addIfMin", new AddIfMin());
    }

    public SenderResult requestFormation(String command, DataForSending object, UserInfo user) {
        Request request = new Request();
        request.setUser(user.getUserLogin());
        request.setCommand(command);
        return commands.get(command).getRequest(object, request);
    }
}