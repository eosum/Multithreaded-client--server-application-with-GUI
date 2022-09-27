package app;

import util.Request;
import commands.*;
import java.util.HashMap;

public class CommandList {
    public final HashMap<String, Command> commands = new HashMap<>();

    public CommandList() {
        commands.put("add", new Add());
        commands.put("info", new Info());
        commands.put("show", new Show());
        commands.put("update", new Update());
        commands.put("remove_by_id", new RemoveByID());
        commands.put("clear", new Clear());
        commands.put("remove_first", new RemoveFirst());
        commands.put("print_ascending", new PrintAscending());
        commands.put("filter_starts_with_soundtrack_name", new FilterStartsWithSoundtrack());
        commands.put("count_less_than_weapon_type", new CountLessThanWeaponType());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("add_if_min", new AddIfMin());
        commands.put("help", new Help());
    }

    public Request requestFormation(String command, String arg) {
        if (commands.containsKey(command)) {
            Request request = commands.get(command).getRequest(arg);
            if (request != null) {
                request.setCommand(command);
            }
            return request;
        }
        System.out.println("Запрос не может быть сформирован. Проверьте вводимые данные");

        return null;
    }
}