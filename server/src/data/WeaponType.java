package data;

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
    public static void outputWeaponType() {
        int cnt = 0;
        for (WeaponType element: WeaponType.values()) {
            System.out.println(element + " - " + cnt);
            cnt++;
        }
    }

    public static WeaponType stringValue(String input) {
        return WeaponType.values()[Integer.parseInt(input)];
    }

    public static WeaponType getWeaponType(String input) {
        return WeaponType.valueOf(input);
    }

}
