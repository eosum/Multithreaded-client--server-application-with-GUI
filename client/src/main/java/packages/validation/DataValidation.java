package packages.validation;

import packages.data.*;

public class DataValidation {

    public boolean validateString(String text) {
        return text != null && !text.equals("") && text.length() <= 10;
    }

    public boolean validateBoolean(String text) {
        return text.equals("true") || text.equals("false");
    }

    public boolean validateCoordinates(Float x, Float y) {
        return Coordinates.checkValidX(x) && Coordinates.checkValidY(y);
    }

    public boolean validateWeaponType(String weaponType) {
        try {
            WeaponType.valueOf(weaponType);
            return true;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }
}
