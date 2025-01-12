package task;

/**
 * This class is used to construct a deadline task.
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static duke.DukeConstants.KEY_SEPARATOR;

public class Deadline extends Task {

    private LocalDate date;

    /**
     * Constructor for the Deadline task.
     *
     * @param description Task description.
     * @param date Deadline of task.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String formatToSave() {
        int value;
        if (getMarked()) {
            value = 1;
        } else {
            value = 0;
        }
        return "D" + KEY_SEPARATOR + value + KEY_SEPARATOR + description + KEY_SEPARATOR + date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
