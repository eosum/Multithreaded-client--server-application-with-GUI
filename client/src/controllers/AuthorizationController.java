package controllers;

import java.io.IOException;

import app.ServerProvider;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.Request;
import util.Response;
import util.SceneSwitch;

public class AuthorizationController {
    ServerProvider serverProvider = ServerProvider.getServerProvider();

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button sign_up_button;

    @FXML
    private Button log_in_button;

    private Request requestForming(String command) {
        Request request = new Request();
        request.setUser(login.getText());
        request.setPassword(password.getText());
        request.setCommand(command);

        return request;
    }

    private void alertForming(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        log_in_button.setOnAction(event -> {
            try {
                serverProvider.send(requestForming("login"));
                Response response = serverProvider.getResponse();
                if(response.isSuccess()) {
                    SceneSwitch.setScene("../recourses/main_page.fxml", "../recourses/css/main_page.css");
                    SceneSwitch.show();
                }
                else {
                    alertForming(response.getMessage());
                }
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        sign_up_button.setOnAction(event -> {
            try {
                serverProvider.send(requestForming("register"));
                Response response = serverProvider.getResponse();
                if(response.isSuccess()) {
                    SceneSwitch.setScene("../recourses/main_page.fxml", "../recourses/css/main_page.css");
                    SceneSwitch.show();
                }
                else {
                    alertForming(response.getMessage());
                }
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}