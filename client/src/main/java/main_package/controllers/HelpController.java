package main_package.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import main_package.util.SceneSwitch;

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

            if (choose.equals("Finish")) {
                try {
                    SceneSwitch.setLocale(new Locale("fi"));
                    SceneSwitch.setScene("../../fxml/help_page.fxml", "../../css/help_page.css");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (choose.equals("English")) {
                try {
                    SceneSwitch.setLocale(new Locale("en"));
                    SceneSwitch.setScene("../../fxml/help_page.fxml", "../../css/help_page.css");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (choose.equals("Espanol")) {
                try {
                    SceneSwitch.setLocale(new Locale("es"));
                    SceneSwitch.setScene("../../fxml/help_page.fxml", "../../css/help_page.css");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    private void fullLanguageChoose() {
        languageChoose.getItems().removeAll(languageChoose.getItems());
        languageChoose.getItems().addAll("English", "Espanol", "Finish");
        languageChoose.getSelectionModel().select("Language");
    }
}
