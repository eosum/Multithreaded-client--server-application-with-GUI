package util;

import java.io.Serializable;
import java.util.LinkedList;

public class Response implements Serializable {
    private static final long serialVersionUID = 12345678910L;
    private String message = " - выполнение команды прошло успешно";
    private Boolean success = false;
    private LinkedList<? extends Serializable> object;

    public String getMessage() {
        return message;
    }
    public LinkedList<? extends Serializable> getObject() {
        return object;
    }

    public Boolean isSuccess() {
        return success;
    }


}
