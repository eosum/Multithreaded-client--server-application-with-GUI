package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneSwitch {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        SceneSwitch.stage = stage;
        stage.setResizable(false);
    }

    public static void setScene(String fxmlURL, String cssURL) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = SceneSwitch.class.getResource(fxmlURL);
        loader.setLocation(xmlUrl);

        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(SceneSwitch.class.getResource(cssURL).toExternalForm());
        stage.setScene(scene);
    }

    public static void show() {
        stage.show();
    }
}
