package app;

import java.util.LinkedList;

import commands.Authorization;
import javafx.application.Application;
import javafx.stage.Stage;
import util.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javafx.*;

public class Main extends Application {
    private static final String STOP_PROGRAM = "exit";

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CommandList commandsList = new CommandList();
        Client client = new Client();
        client.setClient(client);
        Authorization authorization = new Authorization(client);
        client.connect();

        try {
            Scanner input = new Scanner(System.in);

            authorization.start();

            while(true) {
                System.out.print("Введите команду: ");
                String command = input.nextLine();

                String arg = "";
                String[] commandCut = command.trim().split("\\s+");
                String commandName = commandCut[0];

                if (commandCut.length > 1) {
                    arg = commandCut[1];
                }

                if (commandName.equals(STOP_PROGRAM)) {
                    client.close();
                    return;
                }

                Request request = commandsList.requestFormation(commandName, arg, false);
                if (request == null) continue;
                request.setUser(authorization.getUser());
                request.setPassword(authorization.getPassword());

                if (client.isConnected()) {
                    client.sendRequest(request);

                    Response response = client.getResponse();

                    if (response == null) continue;
                    System.out.println(response.getMessage());
                    if (response.getObject() == null) continue;

                    LinkedList<?> object = response.getObject();
                    if (object.size() == 0) {
                        System.out.println("Коллекция пустая");
                    }
                    for (Object o : object) {
                        System.out.println(o);
                    }
                }
                else {
                    client.reconnect();
                }
            }
        }
        catch (NoSuchElementException e) {
            System.out.println("Завершение программы.");
        }
    }
}