package validation;

import data.WeaponType;

public class DataValidation {

    public boolean validateString(String text) {
        return text != null && !text.equals("");
    }

    public boolean validateBoolean(String text) {
        return text.equals("true") || text.equals("false");
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
