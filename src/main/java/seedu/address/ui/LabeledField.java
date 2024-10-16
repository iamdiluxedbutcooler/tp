package seedu.address.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class LabeledField extends HBox {
    private final Label fieldLabel;
    private final Label valueLabel;

    public LabeledField(String fieldName, String value) {
        super(5);
        fieldLabel = new Label(fieldName + ": ");
        fieldLabel.setStyle("-fx-font-weight: bold;");
        valueLabel = new Label(value);

        HBox.setHgrow(valueLabel, Priority.ALWAYS);
        valueLabel.setWrapText(true);

        getChildren().addAll(fieldLabel, valueLabel);
    }

    public void setValue(String value) {
        valueLabel.setText(value);
    }
}
