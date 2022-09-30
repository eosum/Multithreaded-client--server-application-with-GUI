package controllers;

import app.Client;
import app.CommandList;
import app.ServerProvider;
import data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.DataForSending;
import util.Request;
import util.Response;
import util.SenderResult;
import validation.FieldsValidation;
import java.time.LocalDate;

public class MainPageController {
    @FXML
    private Button add;
    @FXML
    private Button addIfMin;
    @FXML
    private Button removeById;
    @FXML
    private Button clear;
    @FXML
    private Button countLess;
    @FXML
    private Button removeFirst;
    @FXML
    private Button help;
    @FXML
    private Button info;
    @FXML
    private Button removeGreater;
    @FXML
    private Button update;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField impact_speed;
    @FXML
    private TextField real_hero;
    @FXML
    private TextField has_toothpick;
    @FXML
    private TextField weapon_type;
    @FXML
    private TextField minutes_of_waiting;
    @FXML
    private TextField x;
    @FXML
    private TextField y;
    @FXML
    private TextField soundtrack;
    @FXML
    private TextField car;
    @FXML
    private Button sendData;
    @FXML
    private TableView<HumanBeingProperty> object_table = new TableView<>();

    @FXML
    private TableColumn<HumanBeingProperty, Number> idColumn;

    @FXML
    private TableColumn<HumanBeingProperty, String> nameColumn;

    @FXML
    private TableColumn<HumanBeingProperty, Number> xColumn;

    @FXML
    private TableColumn<HumanBeingProperty, Number> yColumn;

    @FXML
    private TableColumn<HumanBeingProperty, Boolean> heroColumn;

    @FXML
    private TableColumn<HumanBeingProperty, Boolean> toothpickColumn;

    @FXML
    private TableColumn<HumanBeingProperty, Number> speedColumn;

    @FXML
    private TableColumn<HumanBeingProperty, String> soundtrackColumn;

    @FXML
    private TableColumn<HumanBeingProperty, Number> minutesColumn;

    @FXML
    private TableColumn<HumanBeingProperty, WeaponType> weaponTypeColumn;

    @FXML
    private TableColumn<HumanBeingProperty, LocalDate> creationDateColumn;

    @FXML
    private TableColumn<HumanBeingProperty, String> ownerColumn;

    @FXML
    private TableColumn<HumanBeingProperty, Car> carColumn;

    @FXML
    private TextArea outputField;


    private String command;

    FieldsValidation validator = new FieldsValidation();
    CommandList commandList = new CommandList();
    ServerProvider serverProvider = ServerProvider.getServerProvider();

    public ObservableList<HumanBeingProperty> objects = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        validateFields();
        getHumanBeingObjects();
        tablePreparing();

        add.setOnAction(event -> {
            makeFieldEditable();
            command = "add";
        });

        addIfMin.setOnAction(event -> {
            makeFieldEditable();
            command = "add_if_min";
        });

        removeById.setOnAction(event -> {
            id.setEditable(true);
            command = "remove_by_id";
        });

        clear.setOnAction(event -> {
            command = "clear";
        });

        countLess.setOnAction(event -> {
            command = "count_less_than_weapon_type";
            weapon_type.setEditable(true);
            //showAlert("Эта команда пока в разработке");
        });

        help.setOnAction(event -> {
            showAlert("Эта команда пока в разработке");
        });

        info.setOnAction(event -> {
            command = "info";
            //showAlert("Эта команда пока в разработке");
        });

        removeGreater.setOnAction(event -> {
            id.setEditable(true);
            command = "remove_greater";
        });

        removeFirst.setOnAction(event -> {
            command = "remove_first";
        });

        update.setOnAction(event -> {
            id.setEditable(true);
            makeFieldEditable();
            command = "update";
        });

        sendData.setOnAction(event -> {
            recessFieldEditable();
            SenderResult result = processingRequest();
            if(!result.isResult()) {
                showAlert(result.getMessage());
            }
            else {
                Response response = serverProvider.getResponse();
                outputField.setText(response.getMessage());
            }
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void makeFieldEditable() {
        name.setEditable(true);
        impact_speed.setEditable(true);
        real_hero.setEditable(true);
        has_toothpick.setEditable(true);
        weapon_type.setEditable(true);
        minutes_of_waiting.setEditable(true);
        x.setEditable(true);
        y.setEditable(true);
        soundtrack.setEditable(true);
        car.setEditable(true);
    }

    private void recessFieldEditable() {
        id.setEditable(false);
        name.setEditable(false);
        impact_speed.setEditable(false);
        real_hero.setEditable(false);
        has_toothpick.setEditable(false);
        weapon_type.setEditable(false);
        minutes_of_waiting.setEditable(false);
        x.setEditable(false);
        y.setEditable(false);
        soundtrack.setEditable(false);
        car.setEditable(false);
    }

    private void validateFields() {
        id.setTextFormatter(validator.numericValidation());
        impact_speed.setTextFormatter(validator.numericValidation());
        minutes_of_waiting.setTextFormatter(validator.numericValidation());
        x.setTextFormatter(validator.floatNumericValidation());
        y.setTextFormatter(validator.floatNumericValidation());
    }

    private SenderResult processingRequest() {
        DataForSending object = new DataForSending(id.getText(), name.getText(), x.getText(), y.getText(),
                real_hero.getText(), has_toothpick.getText(), impact_speed.getText(), soundtrack.getText(),
                minutes_of_waiting.getText(), weapon_type.getText(), car.getText());

        return commandList.requestFormation(command, object);
    }

    private void tablePreparing() {
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
        carColumn.setCellValueFactory(cellData -> cellData.getValue().getCarProperty());
        ownerColumn.setCellValueFactory(cellData -> cellData.getValue().getOwnerProperty());
    }

    private void getHumanBeingObjects() {
        new Thread(() -> {
            while(true) {
                Client client = serverProvider.getClient();
                Request request = new Request();
                Response response = null;
                request.setCommand("get_data");

                client.sendRequest(request);

                while(response == null) {
                    response = client.getResponse();
                }

                if (response.getObject() != null) {
                    object_table.getItems().clear();
                    for(HumanBeing element: response.getObject()) {
                        HumanBeingProperty result = new HumanBeingProperty(
                                element.getId(), element.getName(), element.getCoordinates(), element.getRealHero(),
                                element.getHasToothpick(), element.getImpactSpeed(), element.getSoundtrackName(),
                                element.getMinutesOfWaiting(), element.getWeaponType(), element.getCar(), "lol");
                        objects.add(result);
                    }
                }
                object_table.setItems(objects);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}