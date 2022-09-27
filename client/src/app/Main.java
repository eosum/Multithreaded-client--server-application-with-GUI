package app;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import util.*;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            SceneSwitch.setStage(stage);
            SceneSwitch.setScene("../recourses/main_page.fxml", "../recourses/css/main_page.css");
            SceneSwitch.show();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}