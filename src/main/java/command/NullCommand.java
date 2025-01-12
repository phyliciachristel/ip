package command;

import duke.Storage;
import task.TaskList;
import duke.Ui;

/**
 * This class handles unrecognised commands.
 */
public class NullCommand extends Command {
    private String description;
    /**
     * Constructor for the NullCommand class.
     */
    public NullCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     * Displays an error for an unrecognised Command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showError(this.description);
    }

}
