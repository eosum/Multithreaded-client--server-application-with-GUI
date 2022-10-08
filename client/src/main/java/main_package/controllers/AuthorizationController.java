package main_package.controllers;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import main_package.app.ServerProvider;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main_package.util.Request;
import main_package.util.Response;
import main_package.util.SceneSwitch;
import main_package.util.UserInfo;

public class AuthorizationController {
    ServerProvider serverProvider = ServerProvider.getServerProvider();

    @FXML
    private ComboBox languageChoose;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button sign_up_button;

    @FXML
    private Button log_in_button;
    private static UserInfo userInfo;

    private Request requestForming(String command) {
        Request request = new Request();
        userInfo = new UserInfo(login.getText(), password.getText());
        request.setUser(userInfo.getUserLogin());
        request.setPassword(userInfo.getUserPassword());
        request.setCommand(command);

        return request;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static UserInfo getUserInfo() {
        return userInfo;
    }

    @FXML
    private void initialize() {
        fullLanguageChoose();

        languageChoose.setOnAction(event -> {
            String choose = languageChoose.getValue().toString();

            if (choose.equals("Finish")) {
                try {
                    SceneSwitch.setLocale(new Locale("fi"));
                    SceneSwitch.setScene("../../fxml/authorization.fxml", "../../css/authorization_style.css");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (choose.equals("English")) {
                try {
                    SceneSwitch.setLocale(new Locale("en"));
                    SceneSwitch.setScene("../../fxml/authorization.fxml", "../../css/authorization_style.css");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (choose.equals("Espanol")) {
                try {
                    SceneSwitch.setLocale(new Locale("es"));
                    SceneSwitch.setScene("../../fxml/authorization.fxml", "../../css/authorization_style.css");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        log_in_button.setOnAction(event -> {
            try {
                serverProvider.send(requestForming("login"));
                Response response = serverProvider.getResponse();
                if(response.isSuccess()) {
                    SceneSwitch.setScene("../../fxml/main_page.fxml", "../../css/main_page.css");
                    SceneSwitch.show();
                }
                else {
                    showAlert(response.getMessage());
                }
            }
            catch (NullPointerException e) {
                showAlert("Нет соединения с сервером. Попробуйте позже.");
                serverProvider.reconnect();
            }
            catch (IOException e) {
                showAlert("Ошибка в работе приложения");
            }
        });

        sign_up_button.setOnAction(event -> {
            try {
                serverProvider.send(requestForming("register"));
                Response response = serverProvider.getResponse();
                if(response.isSuccess()) {
                    SceneSwitch.setScene("../../fxml/main_page.fxml", "../../css/main_page.css");
                    SceneSwitch.show();
                }
                else {
                    showAlert(response.getMessage());
                }
            }
            catch (NullPointerException e) {
                showAlert("Нет соединения с сервером. Попробуйте позже.");
                serverProvider.reconnect();
            }
            catch (IOException e) {
                showAlert("Ошибка в работе приложения");
            }
        });
    }
    private void fullLanguageChoose() {
        languageChoose.getItems().removeAll(languageChoose.getItems());
        languageChoose.getItems().addAll("English", "Espanol", "Finish");
        languageChoose.getSelectionModel().select("Language");
    }
}