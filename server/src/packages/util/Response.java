package packages.util;

import java.io.Serializable;
import java.nio.channels.SelectionKey;
import java.util.LinkedList;

public class Response implements Serializable {
    private static final long serialVersionUID = 12345678910L;

    private transient SelectionKey key;
    private String message;
    private LinkedList<? extends Serializable> object;

    private Boolean success = true;
    public void setMessage(String message) {
        this.message = message;
    }
    public SelectionKey getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public void setKey(SelectionKey key) {
        this.key = key;
    }
    public void setObject(LinkedList<? extends Serializable> object) {
        this.object = object;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
