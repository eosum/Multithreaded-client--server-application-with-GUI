package packages.controllers;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import packages.app.SceneSwitch;
import packages.connection.Client;
import packages.app.CommandList;
import packages.connection.ServerProvider;
import packages.data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import packages.util.*;
import packages.validation.FieldsValidation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Locale;

public class MainPageController {

    @FXML
    private Label loginInfo;
    @FXML
    private ComboBox languageChoose;

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
    private Button toMap;
    @FXML
    private TableView<HumanBeingProperty> objectTable = new TableView<>();

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
    private Label outputField;

    @FXML
    private GridPane input;

    private String command;

    private UserInfo userInfo;

    FieldsValidation validator = new FieldsValidation();
    CommandList commandList = new CommandList();
    ServerProvider serverProvider = ServerProvider.getServerProvider();

    private LinkedList<TextField> fields = new LinkedList<>();
    public ObservableList<HumanBeingProperty> objects = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        userInfo = AuthorizationController.getUserInfo();
        loginInfo.setText(SceneSwitch.getResourceBundle().getString("userLogin") + userInfo.getUserLogin());

        validateFields();
        getTextFields();
        getHumanBeingObjects();
        tablePreparing();
        fullLanguageChoose();
        objectTable.setOnMouseClicked((event)-> {
            fillTextFields();
        });

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
                SceneSwitch.setScene("../../fxml/main_page.fxml", "../../css/main_page.css");
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        add.setOnAction(event -> {
            makeFieldEditable();
            command = "add";
        });

        addIfMin.setOnAction(event -> {
            makeFieldEditable();
            command = "addIfMin";
        });

        removeById.setOnAction(event -> {
            id.setEditable(true);
            command = "removeById";
        });

        clear.setOnAction(event -> {
            command = "clear";
        });

        countLess.setOnAction(event -> {
            command = "countLessThanWeaponType";
            weapon_type.setEditable(true);
        });

        help.setOnAction(event -> {
            try {
                SceneSwitch.setScene("../../fxml/help_page.fxml", "../../css/help_page.css");
                SceneSwitch.show();
            }
            catch(IOException e) {
                System.out.println(e.getMessage());
            }
        });

        info.setOnAction(event -> {
            command = "info";
        });

        removeGreater.setOnAction(event -> {
            id.setEditable(true);
            command = "removeGreater";
        });

        removeFirst.setOnAction(event -> {
            command = "removeFirst";
        });

        update.setOnAction(event -> {
            id.setEditable(true);
            makeFieldEditable();
            command = "update";
        });

        sendData.setOnAction(event -> {
            if (command != null) {
                recessFieldEditable();
                SenderResult result = processingRequest();
                if(!result.isResult()) {
                    showAlert(result.getMessage());
                }
                else {
                    Response response = null;
                    try {
                        response = serverProvider.getResponse();
                    }
                    catch (NullPointerException | IOException e) {
                        showAlert(SceneSwitch.getResourceBundle().getString("serverConnectionLost"));
                        serverProvider.reconnect();
                    }

                    if(response.isSuccess()) {
                        outputField.setText(SceneSwitch.getResourceBundle().getString("successRequest"));
                        if(response.getMessage() != null) {
                            String[] parts = response.getMessage().split(" ");
                            System.out.println(response.getMessage());
                            if (parts.length == 4) {
                                outputField.setText(SceneSwitch.getResourceBundle().getString("date") + " " + parts[3] + '\n'
                                        + SceneSwitch.getResourceBundle().getString("number") + " " + parts[2] + '\n'
                                        + SceneSwitch.getResourceBundle().getString("typeCollection") + " " + parts[1]);
                            } else {
                                outputField.setText(parts[0]);
                            }

                        }
                    } else {
                        outputField.setText(SceneSwitch.getResourceBundle().getString("error"));
                    }
                }
            }
        });

        toMap.setOnAction(event -> {
            try {
                SceneSwitch.setScene("../../fxml/map_page.fxml", "../../css/map_page.css");
                SceneSwitch.show();
            }
            catch(IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void getTextFields() {
        input.getChildren()
                .filtered(node -> node instanceof TextField)
                .forEach(node -> fields.add((TextField) node));
    }

    private void makeFieldEditable() {
        fields.forEach(element -> element.setEditable(true));
        id.setEditable(false);
    }

    private void recessFieldEditable() {
        fields.forEach(element -> element.setEditable(false));
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
                minutes_of_waiting.getText(), weapon_type.getText(), car.getText(), userInfo.getUserLogin());

        return commandList.requestFormation(command, object, userInfo);
    }

    private void tablePreparing() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        xColumn.setCellValueFactory(cellData -> cellData.getValue().getXProperty());
        yColumn.setCellValueFactory(cellData -> cellData.getValue().getYProperty());
        soundtrackColumn.setCellValueFactory(cellData -> cellData.getValue().getSoundtrackNameProperty());
        heroColumn.setCellValueFactory(cellData -> cellData.getValue().getRealHeroProperty());
        toothpickColumn.setCellValueFactory(cellData -> cellData.getValue().getHasToothpickProperty());
        speedColumn.setCellValueFactory(cellData -> cellData.getValue().getImpactSpeedProperty());
        minutesColumn.setCellValueFactory(cellData -> cellData.getValue().getMinutesOfWaitingProperty());
        weaponTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getWeaponTypeProperty());
        creationDateColumn.setCellValueFactory(cellData -> cellData.getValue().getCreationDateProperty());
        carColumn.setCellValueFactory(cellData -> cellData.getValue().getCarProperty());
        ownerColumn.setCellValueFactory(cellData -> cellData.getValue().getOwnerProperty());
    }

    private void getHumanBeingObjects() {
        Thread thread = new Thread(() -> {
            while(true) {
                Client client = serverProvider.getClient();
                Request request = new Request();
                Response response = null;
                request.setCommand("getData");

                try {
                    client.sendRequest(request);
                    while (response == null) {
                        response = client.getResponse();
                    }
                }
                catch (NullPointerException | IOException e) {
                    showAlert(SceneSwitch.getResourceBundle().getString("serverConnectionLost"));
                    serverProvider.reconnect();
                }

                if (response != null && response.getObject() != null) {
                    objectTable.getItems().clear();
                    for(HumanBeing element: response.getObject()) {
                        HumanBeingProperty result = new HumanBeingProperty(
                                element.getId(), element.getName(), element.getCoordinates(), element.getRealHero(),
                                element.getHasToothpick(), element.getImpactSpeed(), element.getSoundtrackName(),
                                element.getMinutesOfWaiting(), element.getWeaponType(), element.getCar(), element.getOwner());
                        objects.add(result);
                    }
                }
                objectTable.setItems(objects);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    private void fullLanguageChoose() {
        languageChoose.getItems().removeAll(languageChoose.getItems());
        languageChoose.getItems().addAll(Languages.ENGLISH.getLanguage(), Languages.FINNISH.getLanguage(), Languages.SPANISH.getLanguage());
        languageChoose.getSelectionModel().select(SceneSwitch.getResourceBundle().getString("language"));
    }

    private void fillTextFields() {
        try {
            HumanBeingProperty row = objectTable.getSelectionModel().getSelectedItem();
            id.setText(row.getId().toString());
            name.setText(row.getName());
            impact_speed.setText(row.getImpactSpeed().toString());
            real_hero.setText(row.getRealHero().toString());
            has_toothpick.setText(row.getHasToothpick().toString());
            weapon_type.setText(row.getWeaponType());
            minutes_of_waiting.setText(row.getMinutesOfWaiting().toString());
            x.setText(row.getX().toString());
            y.setText(row.getY().toString());
            soundtrack.setText(row.getSoundtrackName());
            car.setText(row.getCar().toString());
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

}