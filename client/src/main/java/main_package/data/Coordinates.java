package main_package.data;

import java.io.Serializable;

/**
 * Coordinates class
 */
public class Coordinates implements Serializable {
    private final static long serialVersionUID = 1123456789L;
    private Float x; // поле не может быть null, max value = 620
    private Float y; // поле не может быть null, max value = 784
    private static final Float max_x = 620F;
    private static final Float max_y = 784F;

    public Coordinates(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @param x coordinate X
     * @return x belongs to the allowed segment
     */
    public static boolean checkValidX(Float x) {
        return x <= max_x;
    }

    /**
     *
     * @param y coordinate Y
     * @return y belongs to the allowed segment
     */
    public static boolean checkValidY (Float y) {
        return y <= max_y;
    }

    /**
     *
     * @return the largest allowed value of x
     */
    public static Float getMaxX() {
        return max_x;
    }

    /**
     *
     * @return the largest allowed value of y
     */
    public static Float getMaxY() {
        return max_y;
    }

    /**
     *
     * @return x
     */
    public Float getX() {
        return x;
    }

    /**
     *
     * @return y
     */
    public Float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        return that.x.equals(x) && that.y.equals(y);
    }

    @Override
    public int hashCode() {
        return x.hashCode() + y.hashCode();
    }

}
