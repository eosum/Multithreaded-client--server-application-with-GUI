package app;

import util.DataForSending;
import util.Request;
import commands.*;
import util.SenderResult;

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
        commands.put("help", new Help());
    }

    public SenderResult requestFormation(String command, DataForSending object) {
        Request request = new Request();
        request.setCommand(command);
        return commands.get(command).getRequest(object, request);
    }
}