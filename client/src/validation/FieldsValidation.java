package validation;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class FieldsValidation {

    public TextFormatter numericValidation() {
        UnaryOperator<TextFormatter.Change> numberValidationFormatter = change -> {
            if (!change.getText().matches("|\\d+")) {
                change.setText("");
                change.setRange(
                        change.getRangeStart(),
                        change.getRangeStart()
                );
            }
            return change;
        };
        return new TextFormatter<>(numberValidationFormatter);
    }

    public TextFormatter floatNumericValidation() {
        UnaryOperator<TextFormatter.Change> floatValidationFormatter = change -> {
            if (!change.getText().matches("|\\d*(\\.\\d*)?")) {
                change.setText("");
                change.setRange(
                        change.getRangeStart(),
                        change.getRangeStart()
                );
            }
            return change;
        };
        return new TextFormatter<>(floatValidationFormatter);
    }

}
