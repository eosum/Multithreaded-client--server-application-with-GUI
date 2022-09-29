package util;

import data.HumanBeing;

import java.io.Serializable;
import java.util.LinkedList;

public class Response implements Serializable {
    private static final long serialVersionUID = 12345678910L;
    private String message = " - выполнение команды прошло успешно";
    private Boolean success = true;
    private LinkedList<HumanBeing> object;

    public String getMessage() {
        return message;
    }
    public LinkedList<HumanBeing> getObject() {
        return object;
    }

    public Boolean isSuccess() {
        return success;
    }


}
