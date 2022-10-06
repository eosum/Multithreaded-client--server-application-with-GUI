package main_package.util;

public class SenderResult {
    private boolean result;
    private String message;

    public SenderResult() {
        result = true;
        message = "Успешно";
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
