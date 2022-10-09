package packages.app;

import packages.commands.*;
import packages.util.Request;
import packages.util.Response;
import packages.util.UserInfo;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CommandsList class
 */
public class CommandsList {
    public final HashMap<String, Command> commands = new HashMap<>();
    ExecutorService executor = Executors.newFixedThreadPool(10);

    /**
     * initializing the list of main_package.commands
     *
     * @param collectionManager the collection manager
     */
    public CommandsList(CollectionManager collectionManager) {
        commands.put("add", new Add(collectionManager));
        commands.put("update", new Update(collectionManager));
        commands.put("removeById", new RemoveById(collectionManager));
        commands.put("clear", new Clear(collectionManager));
        commands.put("info", new Info(collectionManager));
        commands.put("removeFirst", new RemoveFirst(collectionManager));
        commands.put("countLessThanWeaponType", new CountLessThanWeaponType(collectionManager));
        commands.put("removeGreater", new RemoveGreater(collectionManager));
        commands.put("addIfMin", new AddIfMin(collectionManager));
        commands.put("help", new Help(commands, collectionManager));
        commands.put("register", new Register());
        commands.put("login", new Login());
        commands.put("getData", new GetDataFromDB(collectionManager));
    }

    public void execute(Request request) {
        executor.submit(() -> {
            Response response = new Response();
            UserInfo user = new UserInfo(request.getUser(), request.getPassword());
            response.setKey(request.getKey());
            commands.get(request.getCommand()).execute(request.getArg(), request.getObject(), user, response);
        });
    }
}
