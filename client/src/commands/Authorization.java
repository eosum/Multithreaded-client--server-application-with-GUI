package commands;

import app.Client;
import util.Request;
import util.Response;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Authorization {
    private String user;
    private String password;
    private final Client client;

    public Authorization(Client client) {
        this.client = client;
    }
    public void start() {
        Scanner input = new Scanner(System.in);
        try {
            while (true) {
                String command;
                while (true) {
                    System.out.print("Введите register, если у вас нет профиля, иначе login: ");
                    command = input.nextLine().trim();
                    if (command.equals("register") || command.equals("login")) break;
                }
                System.out.print("Введите логин: ");
                String userLogin = input.nextLine().trim();
                System.out.print("Введите пароль: ");
                String userPassword = input.nextLine().trim();

                Request request = new Request();
                request.setCommand(command);
                request.setUser(userLogin);
                request.setPassword(userPassword);

                if(client.isConnected()) {
                    client.sendRequest(request);

                    Response response = client.getResponse();

                    if (response == null) continue;
                    System.out.println(response.getMessage());

                    if (response.getMessage().equals("Успешно")) {
                        this.user = userLogin;
                        this.password = userPassword;
                        break;
                    }
                }
            }
        }
        catch (NoSuchElementException e) {
            System.out.println("Завершение программы.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
