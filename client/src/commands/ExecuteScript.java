package commands;

import util.Response;
import java.io.IOException;
import app.Client;
import java.util.Scanner;
import java.nio.file.Paths;
import util.Request;
import java.nio.file.Path;
import java.util.HashSet;
import checkCorrectInput.CheckCorrectPath;
import app.CommandList;

public class ExecuteScript implements Command {
    private final CommandList commandsList;
    private final CheckCorrectPath checkPathCorrect;
    private HashSet<Path> existingPathList;

    public ExecuteScript(final CommandList commandsList) {
        this.checkPathCorrect = new CheckCorrectPath();
        this.existingPathList = new HashSet<Path>();
        this.commandsList = commandsList;
    }

    @Override
    public Request getRequest(String args, boolean fromFile) {
        try {
            if (!checkPathCorrect.checkPath(args)) {
                return null;
            }

            Path path = Paths.get(args.trim());
            Path base = Paths.get("");
            Path absolutePath = base.resolve(path).toAbsolutePath();

            if (existingPathList.contains(absolutePath)) {
                System.out.println("Вызов скрипта зациклен");
                return null;
            }

            existingPathList.add(absolutePath);
            Scanner inputFromFile = new Scanner(absolutePath);
            while (inputFromFile.hasNext()) {
                String command = inputFromFile.nextLine();

                String arg = "";
                String[] commandCut = command.trim().split("\\s+");
                String commandName = commandCut[0];

                if (commandCut.length > 1) {
                    arg = commandCut[1];
                }

                Request request = commandsList.requestFormation(commandName, arg, true);

                if (Client.getClient().isConnected()) {
                    if (request == null) continue;

                    Client.getClient().sendRequest(request);

                    Response response = Client.getClient().getResponse();
                    System.out.println(response.getMessage());

                    if (response.getObject() == null) continue;

                    System.out.println(response.getObject());
                }
            }
            existingPathList.clear();
        }
        catch (IOException e) {
            System.out.println("Нет доступа к файлу");
        }
        return null;
    }

    @Override
    public String toString() {
        return "execute_script - \u0432\u044b\u043f\u043e\u043b\u043d\u044f\u0435\u0442 \u0432\u0430\u0448 \u0441\u043a\u0440\u0438\u043f\u0442";
    }
}