package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private VBox detailsBox;
    @FXML
    private FlowPane tags;

    private LabeledField phoneField;
    private LabeledField emailField;
    private LabeledField addressField;
    private LabeledField jobField;
    private LabeledField incomeField;
    private LabeledField remarkField;

    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);

        createFields();
        updateFields();

        detailsBox.getChildren().addAll(
                phoneField, emailField, addressField, jobField, incomeField, remarkField
        );

        createTierLabel();
    }

    private void createFields() {
        phoneField = new LabeledField("Phone", "");
        emailField = new LabeledField("Email", "");
        addressField = new LabeledField("Address", "");
        jobField = new LabeledField("Job", "");
        incomeField = new LabeledField("Income", "");
        remarkField = new LabeledField("Remark", "");
    }

    private void updateFields() {
        phoneField.setValue(person.getPhone().value);
        emailField.setValue(person.getEmail().value);
        addressField.setValue(person.getAddress().value);
        jobField.setValue(person.getJob().value);
        incomeField.setValue(person.getIncome().toString());
        remarkField.setValue(person.getRemark().value);
    }

    private void createTierLabel() {
        Label tierLabel = new Label(person.getTier().tagName.toString());
        String tier = person.getTier().tagName.toString().toUpperCase();
        switch (tier) {
        case "GOLD" -> tierLabel.getStyleClass().add("gold-tier");
        case "SILVER" -> tierLabel.getStyleClass().add("silver-tier");
        case "BRONZE" -> tierLabel.getStyleClass().add("bronze-tier");
        case "REJECT" -> tierLabel.getStyleClass().add("reject-tier");
        default -> tierLabel = null;
        }
        if (tierLabel != null) {
            tags.getChildren().add(tierLabel);
        }
    }

    public VBox getDetailedView() {
        return detailsBox;
    }
}