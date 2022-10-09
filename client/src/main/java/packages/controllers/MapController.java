package packages.controllers;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.util.Duration;
import packages.connection.Client;
import packages.connection.ServerProvider;
import packages.data.HumanBeing;
import packages.util.Languages;
import packages.util.Request;
import packages.util.Response;
import packages.app.SceneSwitch;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class MapController {

    @FXML
    private Canvas map;

    @FXML
    private ComboBox languageChoose;
    @FXML
    private Button backButton;
    GraphicsContext context;
    HashMap<String, Color> ownerColor = new HashMap<>();
    volatile HashMap<HumanBeing, String> objectsHistory = new HashMap<>();
    volatile LinkedList<HumanBeing> currentObjects = new LinkedList<>();
    ServerProvider serverProvider = ServerProvider.getServerProvider();

    @FXML
    private void initialize() {
        getObjects();
        context = map.getGraphicsContext2D();
        startDrawing();

        map.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            AtomicReference<HumanBeing> element = new AtomicReference<>();
            synchronized (objectsHistory) {
                objectsHistory.forEach((key, value) -> {
                    double keyX = key.getCoordinateX();
                    double keyY = key.getCoordinateY();
                    if (x >= keyX && x <= keyX + 140 && y >= keyY && y <= keyY + 90) {
                        element.set(key);
                    }
                });
            }
            elementInfoOutput(element);
        });

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

    private void getObjects() {
        Thread thread = new Thread(() -> {
            Client client = serverProvider.getClient();
            while(true) {
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
                    System.out.println(SceneSwitch.getResourceBundle().getString("serverConnectionLost"));
                    serverProvider.reconnect();
                }

                if (response != null && response.getObject() != null) {
                    synchronized (currentObjects) {
                        currentObjects = response.getObject();
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    private void startDrawing() {
        new Thread(() -> {
            while(true) {
                updateCollection();
                renderMap();
            }
        }).start();
    }

    private void renderMap() {
        Iterator<Map.Entry<HumanBeing, String>> iterator = objectsHistory.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<HumanBeing, String> pair = iterator.next();
            if (pair.getValue().equals("draw")) {
                objectsHistory.put(pair.getKey(), "exist");
                drawGhost(pair.getKey());
            }
            if (pair.getValue().equals("remove")) {
                LinkedList<HumanBeing> drawAgain = new LinkedList<>();
                objectsHistory.forEach((key, value) -> {
                    float keyX = key.getCoordinateX();
                    float keyY = key.getCoordinateY();
                    float pairX = pair.getKey().getCoordinateX();
                    float pairY = pair.getKey().getCoordinateY();
                    if (keyX + 20 >=  pairX && keyX + 20 <= pairX + 170) {
                        if (keyY + 20 >=  pairY && keyY + 20 <= pairY + 100) {
                            if (!key.equals(pair.getKey())) {
                                drawAgain.add(key);
                            }
                        }
                    }
                });

                deleteGhost(pair.getKey(), drawAgain);
                iterator.remove();
            }
        }
    }

    private void deleteGhost(HumanBeing element, LinkedList<HumanBeing> drawAgain) {
        drawCross(element, drawAgain);
    }

    private void fullLanguageChoose() {
        languageChoose.getItems().removeAll(languageChoose.getItems());
        languageChoose.getItems().addAll(Languages.ENGLISH.getLanguage(), Languages.FINNISH.getLanguage(), Languages.SPANISH.getLanguage());
        languageChoose.getSelectionModel().select(SceneSwitch.getResourceBundle().getString("language"));
    }

    private void updateCollection() {
        if (objectsHistory.isEmpty()) {
            for (HumanBeing element : currentObjects) {
                drawGhost(element);
                objectsHistory.put(element, "exist");
            }
        }
        else {
            synchronized (currentObjects) {
                objectsHistory.forEach((key, value) -> {
                    if (!currentObjects.contains(key)) {
                        objectsHistory.put(key, "remove");
                    }
                });

                for (HumanBeing element : currentObjects) {
                    if (!objectsHistory.containsKey(element)) {
                        objectsHistory.put(element, "draw");
                    }
                }
            }
        }
    }

    private void drawGhost(HumanBeing element) {
        int deltaXFirstEye = 42;
        int deltaXSecondEye = 70;
        int deltaY = 25;
        Paint p = context.getFill();

        if (!ownerColor.containsKey(element.getOwner())) {
            ownerColor.put(element.getOwner(), Color.color(Math.random(), Math.random(), Math.random()));
        }
        Color color = ownerColor.get(element.getOwner());
        context.setFill(color);
        context.fillRect(element.getCoordinateX() + deltaXFirstEye, element.getCoordinateY() + deltaY, 25, 30);
        context.fillRect(element.getCoordinateX() + deltaXSecondEye, element.getCoordinateY() + deltaY, 25, 30);
        context.setFill(p);
        Image imageP = new Image("image/ghost.png");
        context.drawImage(imageP, element.getCoordinateX(), element.getCoordinateY(), 140, 90);
    }

    private void drawCross(HumanBeing element, LinkedList<HumanBeing> drawAgain) {
        int deltaX = -4;
        int deltaY = -7;
        int deltaX1 = 120;
        int deltaY1 = 85;
        final double diameter = 10;
        float startedX = element.getCoordinateX() + deltaX;
        float startedY = element.getCoordinateY() + deltaY;
        float endedX = element.getCoordinateX() + deltaX1;
        float endedY = element.getCoordinateY() + deltaY1;
        DoubleProperty x  = new SimpleDoubleProperty(startedX);
        DoubleProperty y  = new SimpleDoubleProperty(startedY);

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0), new KeyValue(x, startedX), new KeyValue(y, startedY)),
            new KeyFrame(Duration.seconds(2), new KeyValue(x, endedX), new KeyValue(y, endedY)),
            new KeyFrame(Duration.seconds(2), event ->
                    context.clearRect(element.getCoordinateX()-10, element.getCoordinateY()-10, 170, 130)),
            new KeyFrame(Duration.seconds(2), event -> drawAgain.forEach(this::drawGhost))
        );

        timeline.setAutoReverse(true);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                context.setFill(Color.FORESTGREEN);
                context.fillOval(x.doubleValue(), y.doubleValue(), diameter, diameter);
            }
        };
        timer.start();
        timeline.play();
    }

    private void elementInfoOutput(AtomicReference<HumanBeing> element) {
        if(element.get() != null) {
            Popup popup = new Popup();
            TextArea info = new TextArea();
            ResourceBundle rb = SceneSwitch.getResourceBundle();
            info.setText(
                    "" + "\n"
                            + "ID: " + element.get().getId() + "\n"
                            + rb.getString("name") + ": " + element.get().getName() + "\n"
                            + "X: " + element.get().getCoordinateX() + "\n"
                            + "Y: " + element.get().getCoordinateY() + "\n"
                            + rb.getString("realHero") + ": " + "\n"
                            + rb.getString("toothpick") + ": " + element.get().getHasToothpick() + "\n"
                            + rb.getString("impactSpeed") + ": " + element.get().getImpactSpeed() + "\n"
                            + rb.getString("soundtrack") + ": " + element.get().getSoundtrackName() + "\n"
                            + rb.getString("minutes") + ": " + element.get().getMinutesOfWaiting() + "\n"
                            + rb.getString("weaponType") + ": " + element.get().getWeaponType() + "\n"
                            + rb.getString("car") + ": " + element.get().getCar() + "\n"
                            + rb.getString("creationDate") + ": " + element.get().getCreationDate() + "\n"
                            + rb.getString("owner") + ": " + element.get().getOwner()
                            + ""
            );

            info.setEditable(false);

            popup.getContent().add(info);
            popup.setAutoHide(true);
            if (!popup.isShowing()) popup.show(SceneSwitch.getStage());
        }
    }


}
