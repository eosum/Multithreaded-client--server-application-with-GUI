package main_package.app;

import main_package.commands.*;
import main_package.util.DataForSending;
import main_package.util.Request;
import main_package.util.SenderResult;
import main_package.util.UserInfo;

import java.util.HashMap;

public class CommandList {
    public final HashMap<String, Command> commands = new HashMap<>();

    public CommandList() {
        commands.put("add", new Add());
        commands.put("update", new Update());
        commands.put("remove_by_id", new RemoveByID());
        commands.put("clear", new Clear());
        commands.put("remove_first", new RemoveFirst());
        commands.put("count_less_than_weapon_type", new CountLessThanWeaponType());
        commands.put("info", new Info());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("add_if_min", new AddIfMin());
    }

    public SenderResult requestFormation(String command, DataForSending object, UserInfo user) {
        Request request = new Request();
        request.setUser(user.getUserLogin());
        request.setCommand(command);
        return commands.get(command).getRequest(object, request);
    }
}