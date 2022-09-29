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

    /*for (int i = 1; i < 10; i++) {
           HumanBeing human = new HumanBeing(1L, "kek", new Coordinates(1F, 1F), true, true, 1L, "1", 1, WeaponType.KNIFE, new Car("1"), "lol");
           objects.add(human);
       }
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        xColumn.setCellValueFactory(cellData -> cellData.getValue().getXProperty());
        yColumn.setCellValueFactory(cellData -> cellData.getValue().getYProperty());
        heroColumn.setCellValueFactory(cellData -> cellData.getValue().getRealHeroProperty());
        toothpickColumn.setCellValueFactory(cellData -> cellData.getValue().getHasToothpickProperty());
        speedColumn.setCellValueFactory(cellData -> cellData.getValue().getImpactSpeedProperty());
        soundtrackColumn.setCellValueFactory(cellData -> cellData.getValue().getSoundtrackNameProperty());
        minutesColumn.setCellValueFactory(cellData -> cellData.getValue().getMinutesOfWaitingProperty());
        weaponTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getWeaponTypeProperty());
        creationDateColumn.setCellValueFactory(cellData -> cellData.getValue().getCreationDateProperty());
        ownerColumn.setCellValueFactory(cellData -> cellData.getValue().getOwnerProperty());
        carColumn.setCellValueFactory(cellData -> cellData.getValue().getCarProperty());

        object_table.setItems(objects);
*/