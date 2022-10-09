package packages.util;

public class SenderResult {
    private boolean result;
    private String message;

    public SenderResult() {
        result = true;
        message = "";
    }

    public SenderResult(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
