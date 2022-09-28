package data;

import javafx.beans.property.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * HumanBeing class
 */
public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    private final static long serialVersionUID = 1234567891011L;
    private LongProperty id; // поле не может быть null, значение генерируется автоматически, уникально, > 0
    private StringProperty name; // поле не может быть 0, строка не пустая
    private FloatProperty x; // поле не может быть null
    private FloatProperty y;
    private ObjectProperty<ZonedDateTime> creationDate; //
    private BooleanProperty realHero; // поле не может быть null
    private BooleanProperty hasToothpick; // поле может быть null
    private LongProperty impactSpeed; // поле может быть null
    private StringProperty soundtrackName; // поле не может быть null
    private IntegerProperty minutesOfWaiting; // поле может быть null
    private ObjectProperty<WeaponType> weaponType; // поле может быть null
    private ObjectProperty<Car> car; // поле не может быть null
    private StringProperty owner;

    public HumanBeing(Long id, String name, Coordinates coordinates, Boolean realHero, Boolean hasToothpick, Long impactSpeed,
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

        creationDate = new SimpleObjectProperty<>(ZonedDateTime.now());
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

    public StringProperty getOwnerProperty() {
        return owner;
    }

    public ObjectProperty<WeaponType> getWeaponTypeProperty() {
        return weaponType;
    }


    /**
     * @return creation date
     */
    public ZonedDateTime getCreationDate() {
        return creationDate.get();
    }

    public ObjectProperty<ZonedDateTime> getCreationDateProperty() {
        return creationDate;
    }

    /**
     * @return the brand of the car
     */

    public Car getCar() {
        return car.get();
    }

    /**
     * @return number of waiting minutes
     */
    public Integer getMinutesOfWaiting() {
        return minutesOfWaiting.get();
    }

    public IntegerProperty getMinutesOfWaitingProperty() {
        return minutesOfWaiting;
    }

    /**
     * @return impact speed
     */
    public LongProperty getImpactSpeedProperty() {
        return impactSpeed;
    }

    public Long getImpactSpeed() {
        return impactSpeed.get();
    }

    /**
     * @return has toothpick or not
     */
    public Boolean getHasToothpick() {
        return hasToothpick.get();
    }

    public BooleanProperty getHasToothpickProperty() {
        return hasToothpick;
    }
    /**
     * @return element's id
     */
    public Long getId() {
        return id.get();
    }

    public LongProperty getIdProperty() {
        return id;
    }

    /**
     * @return element's name
     */
    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    /**
     * @return is element real hero
     */
    public Boolean getRealHero() {
        return realHero.get();
    }

    public BooleanProperty getRealHeroProperty() {
        return realHero;
    }
    /**
     *
     * @return element's soundtrack
     */
    public String getSoundtrackName() {
        return soundtrackName.get();
    }

    public StringProperty getSoundtrackNameProperty() {
        return soundtrackName;
    }
    /**
     *
     * @return element's weapon type
     */
    public String getWeaponType() {
        return weaponType.toString();
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
    public int compareTo(HumanBeing o) {
        if (this.id == o.id) return 0;
        if (this.id.get() < o.id.get()) return -1;
        return 1;
    }
}
