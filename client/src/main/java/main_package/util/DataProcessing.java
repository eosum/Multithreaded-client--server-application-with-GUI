package main_package.util;

import main_package.data.*;
import main_package.validation.DataValidation;

public class DataProcessing {

    public HumanBeing dataProcessing(DataForSending object) {
        if (checkData(object)) {
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
            String user = object.getUser();

            return new HumanBeing(id, name, new Coordinates(x, y), realHero, hasToothpick, impactSpeed,
                    soundtrackName, minutesOfWaiting, WeaponType.valueOf(weaponType), new Car(car), user);
        }
        return null;
    }

    private boolean checkData(DataForSending object) {
        DataValidation validator = new DataValidation();
        if (!validator.validateString(object.getId())) object.setId("-1");
        boolean result = validator.validateString(object.getId()) && validator.validateString(object.getCar())
                && validator.validateBoolean(object.getHasToothpick()) && validator.validateBoolean(object.getRealHero())
                && validator.validateString(object.getName()) && validator.validateString(object.getImpactSpeed())
                && validator.validateString(object.getMinutesOfWaiting()) && validator.validateString(object.getX())
                && validator.validateString(object.getY()) && validator.validateString(object.getSoundtrackName())
                && validator.validateWeaponType(object.getWeaponType())
                && validator.validateCoordinates(Float.valueOf(object.getX()), Float.valueOf(object.getY()));
        return result;
    }
}
