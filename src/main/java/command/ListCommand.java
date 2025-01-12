package command;

import duke.Storage;
import task.TaskList;
import duke.Ui;

/**
 * This class handles all commands related to listing tasks
 * and inherits from the Command class.
 */
public class ListCommand extends Command {
    /**
     * Constructor for the ListCommand class.
     */
    public ListCommand() {

    }

    /**
     * {@inheritDoc}
     * Lists all the tasks in the tasklist.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printTasks(taskList);
    }

}
