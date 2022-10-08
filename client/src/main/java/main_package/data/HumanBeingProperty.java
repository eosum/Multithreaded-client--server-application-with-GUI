package main_package.data;

import javafx.beans.property.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * HumanBeing class
 */
public class HumanBeingProperty implements Comparable<HumanBeingProperty>, Serializable {
    private final static long serialVersionUID = 1234567891011L;
    private LongProperty id; // поле не может быть null, значение генерируется автоматически, уникально, > 0
    private StringProperty name; // поле не может быть 0, строка не пустая
    private FloatProperty x; // поле не может быть null
    private FloatProperty y;
    private ObjectProperty<LocalDate> creationDate; //
    private BooleanProperty realHero; // поле не может быть null
    private BooleanProperty hasToothpick; // поле может быть null
    private LongProperty impactSpeed; // поле может быть null
    private StringProperty soundtrackName; // поле не может быть null
    private IntegerProperty minutesOfWaiting; // поле может быть null
    private ObjectProperty<WeaponType> weaponType; // поле может быть null
    private ObjectProperty<Car> car; // поле не может быть null
    private StringProperty owner;

    public HumanBeingProperty(Long id, String name, Coordinates coordinates, Boolean realHero, Boolean hasToothpick, Long impactSpeed,
                      String soundtrackName, Integer minutesOfWaiting, WeaponType weaponType, Car car, String owner) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.x = new SimpleFloatProperty(coordinates.getX());
        this.y = new SimpleFloatProperty(coordinates.getY());
        this.realHero = new SimpleBooleanProperty(realHero);
        this.hasToothpick = new SimpleBooleanProperty(hasToothpick);
        this.impactSpeed = new SimpleLongProperty(impactSpeed);
        this.soundtrackName = new SimpleStringProperty(soundtrackName);
        this.minutesOfWaiting = new SimpleIntegerProperty(minutesOfWaiting);
        this.weaponType = new SimpleObjectProperty<>(weaponType);
        this.car = new SimpleObjectProperty<>(car);
        this.owner = new SimpleStringProperty(owner);

        creationDate = new SimpleObjectProperty<>(LocalDate.now());
    }

    public ObjectProperty<Car> getCarProperty() {
        return car;
    }

    public FloatProperty getXProperty() {
        return x;
    }

    public FloatProperty getYProperty() {
        return y;
    }

    public ObjectProperty<WeaponType> getWeaponTypeProperty() {
        return weaponType;
    }

    public ObjectProperty<LocalDate> getCreationDateProperty() {
        return creationDate;
    }

    public Car getCar() {
        return car.get();
    }

    public Float getX() {
        return x.get();
    }

    public Float getY() {
        return y.get();
    }

    public Integer getMinutesOfWaiting() {
        return minutesOfWaiting.get();
    }

    public IntegerProperty getMinutesOfWaitingProperty() {
        return minutesOfWaiting;
    }

    public LongProperty getImpactSpeedProperty() {
        return impactSpeed;
    }

    public Long getImpactSpeed() {
        return impactSpeed.get();
    }

    public Boolean getHasToothpick() {
        return hasToothpick.get();
    }

    public BooleanProperty getHasToothpickProperty() {
        return hasToothpick;
    }

    public Long getId() {
        return id.get();
    }

    public LongProperty getIdProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public StringProperty getOwnerProperty() {
        return owner;
    }

    public Boolean getRealHero() {
        return realHero.get();
    }

    public BooleanProperty getRealHeroProperty() {
        return realHero;
    }

    public String getSoundtrackName() {
        return soundtrackName.get();
    }

    public StringProperty getSoundtrackNameProperty() {
        return soundtrackName;
    }
    public String getWeaponType() {
        return weaponType.get().toString();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return "id = " + id.get() + "\n"
                + "name = " + name.get() + "\n"
                + "coordinates: " + x + " " + y + "\n"
                + "realHero = " + realHero.get() + "\n"
                + "hasToothpick = " +  hasToothpick.get() + "\n"
                + "impactSpeed = " + impactSpeed.get() + "\n"
                + "soundtrackName = " + soundtrackName.get() + "\n"
                + "minutesOfWaiting = " + minutesOfWaiting.get() + "\n"
                + "weaponType = " + weaponType.get() + "\n"
                + "car = " + car.get() + "\n"
                + "creationDate = " + creationDate.get();
    }

    @Override
    public int compareTo(HumanBeingProperty o) {
        if (this.id.get() == o.id.get()) return 0;
        if (this.id.get() < o.id.get()) return -1;
        return 1;
    }
}
