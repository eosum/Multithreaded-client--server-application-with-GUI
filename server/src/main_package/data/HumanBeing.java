package main_package.data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * HumanBeing class
 */
public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    private final static long serialVersionUID = 1234567891011L;
    private Long id; // поле не может быть null, значение генерируется автоматически, уникально, > 0
    private String name; // поле не может быть 0, строка не пустая
    private Coordinates coordinates; // поле не может быть null
    private LocalDate creationDate; //
    private Boolean realHero; // поле не может быть null
    private Boolean hasToothpick; // поле может быть null
    private Long impactSpeed; // поле может быть null
    private String soundtrackName; // поле не может быть null
    private Integer minutesOfWaiting; // поле может быть null
    private WeaponType weaponType; // поле может быть null
    private Car car; // поле не может быть null
    private String owner;

    public HumanBeing(Long id, String name, Coordinates coordinates, Boolean realHero, Boolean hasToothpick, Long impactSpeed,
                      String soundtrackName, Integer minutesOfWaiting, WeaponType weaponType, Car car, String owner) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.soundtrackName = soundtrackName;
        this.minutesOfWaiting = minutesOfWaiting;
        this.weaponType = weaponType;
        this.car = car;
        this.owner = owner;

        creationDate = LocalDate.now();
    }

    public String getOwner() {
        return owner;
    }
    /**
     * @return x coordinate
     */
    public Float getCoordinateX() {
        return coordinates.getX();
    }

    /**
     * @return y coordinate
     */
    public Float getCoordinateY() {
        return coordinates.getY();
    }

    /**
     * @return creation date
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * @return the brand of the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * @return number of waiting minutes
     */
    public Integer getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    /**
     * @return impact speed
     */
    public Long getImpactSpeed() {
        return impactSpeed;
    }

    /**
     * @return has toothpick or not
     */
    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    /**
     * @return element's id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return element's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return is element real hero
     */
    public Boolean getRealHero() {
        return realHero;
    }

    /**
     *
     * @return element's soundtrack
     */
    public String getSoundtrackName() {
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
        this.id = id;
    }

    @Override
    public String toString() {
        return "id = " + id + "\n"
                + "name = " + name + "\n"
                + "coordinates: " + coordinates + "\n"
                + "realHero = " + realHero + "\n"
                + "hasToothpick = " +  hasToothpick+ "\n"
                + "impactSpeed = " + impactSpeed + "\n"
                + "soundtrackName = " + soundtrackName + "\n"
                + "minutesOfWaiting = " + minutesOfWaiting + "\n"
                + "weaponType = " + weaponType + "\n"
                + "car = " + car + "\n"
                + "creationDate = " + creationDate;
    }

    @Override
    public int compareTo(HumanBeing o) {
        if (this.id == o.id) return 0;
        if (this.id < o.id) return -1;
        return 1;
    }
}