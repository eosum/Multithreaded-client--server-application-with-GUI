package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.ServerProvider;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import util.Request;
import util.Response;
import util.SceneSwitch;

public class AuthorizationController {
    ServerProvider serverProvider = ServerProvider.getServerProvider();

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private URL location;

    @FXML
    private AnchorPane body;

    @FXML
    private Label login_description;

    @FXML
    private Label page_name;

    @FXML
    private Label password_description;

    @FXML
    private Button sign_up_button;

    @FXML
    private Button log_in_button;

    private Request requestForming(String login, String password, String command) {
        Request request = new Request();
        request.setUser(login);
        request.setPassword(password);
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
                serverProvider.send(requestForming(login.getText(), password.getText(), "login"));
                Response response = serverProvider.getResponse();
                if(response.isSuccess()) {
                    SceneSwitch.setScene("../recourses/main_page.fxml", "../recourses/css/main_page.css");
                    SceneSwitch.show();
                }
                else {
                    alertForming(response.getMessage());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}