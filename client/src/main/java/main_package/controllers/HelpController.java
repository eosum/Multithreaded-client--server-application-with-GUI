package main_package.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import main_package.app.SceneSwitch;
import main_package.util.Languages;

import java.io.IOException;
import java.util.Locale;

public class HelpController {
    @FXML
    private Button backButton;

    @FXML
    private ComboBox languageChoose;

    @FXML
    private void initialize() {
        backButton.setOnAction(event -> {
            try {
                SceneSwitch.setScene("../../fxml/main_page.fxml", "../../css/main_page.css");
                SceneSwitch.show();
            }
            catch(IOException e) {
                System.out.println(e.getMessage());
            }
        });

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
                SceneSwitch.setScene("../../fxml/map_page.fxml", "../../css/map_page.css");
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void fullLanguageChoose() {
        languageChoose.getItems().removeAll(languageChoose.getItems());
        languageChoose.getItems().addAll(Languages.ENGLISH.getLanguage(), Languages.FINNISH.getLanguage(), Languages.SPANISH.getLanguage());
        languageChoose.getSelectionModel().select(SceneSwitch.getResourceBundle().getString("language"));
    }
}
