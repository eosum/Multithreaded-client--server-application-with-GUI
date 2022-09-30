package util;

import data.Car;
import data.Coordinates;
import data.HumanBeing;
import data.WeaponType;
import validation.DataValidation;

public class DataProcessing {

    public HumanBeing dataProcessing(DataForSending object) {
        DataValidation validator = new DataValidation();
        if (!validator.validateString(object.getId())) object.setId("-1");
        boolean result = validator.validateString(object.getId()) && validator.validateString(object.getCar())
                        && validator.validateBoolean(object.getHasToothpick()) && validator.validateBoolean(object.getRealHero())
                        && validator.validateString(object.getName()) && validator.validateString(object.getImpactSpeed())
                        && validator.validateString(object.getMinutesOfWaiting()) && validator.validateString(object.getX())
                        && validator.validateString(object.getY()) && validator.validateString(object.getSoundtrackName())
                        && validator.validateWeaponType(object.getWeaponType());

        if (result) {
            Long id = Long.parseLong(object.getId());
            String name = object.getName();
            Float x = Float.parseFloat(object.getX());
            Float y = Float.parseFloat(object.getY());;
            Boolean realHero = Boolean.parseBoolean(object.getRealHero());
            Boolean hasToothpick = Boolean.parseBoolean(object.getHasToothpick());;
            Long impactSpeed = Long.parseLong(object.getImpactSpeed());
            String soundtrackName = object.getSoundtrackName();
            Integer minutesOfWaiting = Integer.parseInt(object.getMinutesOfWaiting());
            String weaponType = object.getWeaponType();
            String car = object.getCar();

            return new HumanBeing(id, name, new Coordinates(x, y), realHero, hasToothpick, impactSpeed,
                    soundtrackName, minutesOfWaiting, WeaponType.valueOf(weaponType), new Car(car), "lol");
        }
        return null;
    }
}
