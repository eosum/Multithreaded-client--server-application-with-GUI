package util;

public class DataForSending {
    private String id;
    private String name;
    private String x;
    private String y;
    private String realHero;
    private String hasToothpick;
    private String impactSpeed;
    private String soundtrackName;
    private String minutesOfWaiting;
    private String weaponType;
    private String car;

    public DataForSending(String id, String name, String x, String y, String realHero, String hasToothpick,
                          String impactSpeed, String soundtrackName, String minutesOfWaiting,
                          String weaponType, String car) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.soundtrackName = soundtrackName;
        this.minutesOfWaiting = minutesOfWaiting;
        this.weaponType = weaponType;
        this.car = car;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getRealHero() {
        return realHero;
    }

    public String getHasToothpick() {
        return hasToothpick;
    }

    public String getImpactSpeed() {
        return impactSpeed;
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public String getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public String getCar() {
        return car;
    }

    public void setId(String id) {
        this.id = id;
    }
}
