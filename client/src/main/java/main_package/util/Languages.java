package main_package.util;

public enum Languages {
    ENGLISH("English"),
    FINNISH("Finnish"),
    SPANISH("Spanish");

    private String language;
    Languages(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
