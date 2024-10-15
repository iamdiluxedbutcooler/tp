package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label job;
    @FXML
    private Label email;
    @FXML
    private Label income;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        job.setText(person.getJob().value);
        income.setText(person.getIncome().toString());
        remark.setText(person.getRemark().value);

        // Create a label for the tier tag
        Label tierLabel = new Label(person.getTier().tagName.toString());

        // Apply a different style class based on the tier value
        String tier = person.getTier().tagName.toString().toUpperCase();
        switch (tier) {
        case "GOLD" -> tierLabel.getStyleClass().add("gold-tier");
        case "SILVER" -> tierLabel.getStyleClass().add("silver-tier");
        case "BRONZE" -> tierLabel.getStyleClass().add("bronze-tier");
        case "REJECT" -> tierLabel.getStyleClass().add("reject-tier");
        default -> tierLabel = null;
        }
        if (tierLabel != null) {
            // Add the label to the FlowPane
            tags.getChildren().add(tierLabel);
        }
    }

}
