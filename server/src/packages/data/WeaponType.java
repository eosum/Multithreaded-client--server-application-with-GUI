package packages.data;

import java.io.Serializable;

/**
 * WeaponType enum
 */
public enum WeaponType implements Serializable {
    HAMMER,
    SHOTGUN,
    KNIFE,
    BAT;
    private final static long serialVersionUID = 1234567L;

    public static WeaponType getWeaponType(String input) {
        return WeaponType.valueOf(input);
    }

}
