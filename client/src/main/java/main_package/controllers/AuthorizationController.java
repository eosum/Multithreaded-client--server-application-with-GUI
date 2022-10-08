package main_package.controllers;

import java.io.IOException;
import java.util.Locale;

import main_package.serverConnection.ServerProvider;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main_package.util.Languages;
import main_package.util.Request;
import main_package.util.Response;
import main_package.app.SceneSwitch;
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
            try {
                if (choose.equals(Languages.FINNISH.getLanguage())) {
                    SceneSwitch.setLocale(new Locale("fi"));
                }
                if (choose.equals(Languages.ENGLISH.getLanguage())) {
                    SceneSwitch.setLocale(new Locale("en"));
                }
                if (choose.equals(Languages.SPANISH.getLanguage())) {
                    SceneSwitch.setLocale(new Locale("es"));
                }
                SceneSwitch.setScene("../../fxml/authorization.fxml", "../../css/authorization.css");
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
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
                    showAlert(SceneSwitch.getResourceBundle().getString("wrongUserInfo"));
                }
            }
            catch (NullPointerException | IOException e) {
                showAlert(SceneSwitch.getResourceBundle().getString("serverConnectionLost"));
                serverProvider.reconnect();
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
                    showAlert(SceneSwitch.getResourceBundle().getString("wrongSignInfo"));
                }
            }
            catch (NullPointerException | IOException e) {
                showAlert(SceneSwitch.getResourceBundle().getString("serverConnectionLost"));
                serverProvider.reconnect();
            }
        });
    }
    private void fullLanguageChoose() {
        languageChoose.getItems().removeAll(languageChoose.getItems());
        languageChoose.getItems().addAll(Languages.ENGLISH.getLanguage(), Languages.FINNISH.getLanguage(), Languages.SPANISH.getLanguage());
        languageChoose.getSelectionModel().select(SceneSwitch.getResourceBundle().getString("language"));
    }
}