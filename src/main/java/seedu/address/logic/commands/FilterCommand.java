package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains the substring.
 * Substring matching is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches for all customers whose specified field "
            + "contains the given substring (case-insensitive) and displays the results in a numbered list.\n"
            + "Parameters: <FLAG>/ <SEARCH TERM>\n"
            + "Flags: n/ (name), p/ (phone), e/ (email), a/ (address), j/ (job), r/ (remarks)\n"
            + "Example: " + COMMAND_WORD + " n/ Alice\n"
            + "This will find all customers whose names contain 'Alice'.";

    public static final String MULTIPLE_FILTERS_NOT_IMPLEMENTED = "Using multiple filters is not supported yet. "
            + "Please use only one filter.";

    private final Predicate<Person> predicate;

    public FilterCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicates", predicate)
                .toString();
    }
}
