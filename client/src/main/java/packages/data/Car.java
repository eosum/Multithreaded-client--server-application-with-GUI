package packages.data;

import java.io.Serializable;

/**
 * Car class
 */
public class Car implements Serializable {
    private final static long serialVersionUID = 12345678L;
    private String name; // поле не может быть null

    public Car (String car) {
        this.name = car ;
    }

    @Override
    public String toString() {
        return name;
    }
}
