package packages.app;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class SceneSwitch {
    private static Stage stage;
    private static Locale locale = new Locale("");
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n/l10n", locale);

    public static void setStage(Stage stage) {
        SceneSwitch.stage = stage;
        stage.setResizable(false);
    }

    public static Stage getStage() {
        return stage;
    }
    public static void setLocale(Locale loc) {
        locale = loc;
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public static void setScene(String fxmlURL, String cssURL) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = SceneSwitch.class.getResource(fxmlURL);
        loader.setLocation(xmlUrl);

        resourceBundle = ResourceBundle.getBundle("l10n/l10n", locale);
        loader.setResources(resourceBundle);

        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(SceneSwitch.class.getResource(cssURL).toExternalForm());
        stage.setScene(scene);
    }

    public static void show() {
        stage.show();
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
