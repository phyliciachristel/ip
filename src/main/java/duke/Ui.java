package duke;

import task.Task;
import task.TaskList;

/**
 * This class handles the display for user interaction.
 */
public class Ui {

    public final String ADDED = "oke i added this:";
    public final String DELETED = "oke i deleted this:";
    public final String MARKED = "oke this is done now:";
    public final String UNMARKED = "oke this is undone now:";
    public final String ARCHIVED_TASK = "this has been archived:";


    /**
     * Constructor for the Ui class.
     */
    public Ui () {

    }


    /**
     * Prints an exit message.
     *
     * @return String message.
     */
    public String showExit() {
        return "bye see u\n";
    }

    /**
     * Displays a task.
     *
     * @param message Message to be printed.
     * @param task Task to be displayed.
     * @return String displaying the task.
     */
    public String displayTask(String message, Task task) {
        return message + "\n" + task + "\n";
    }

    /**
     * Prints the entire list of tasks.
     *
     * @param taskList Tasklist to be printed.
     * @return String containing all the tasks.
     */
    public String printTasks(TaskList taskList) {
        int size = taskList.getSize();
        if (size == 0) {
            return "u currently have no tasks!";
        }
        return "here! u have " + size + " task(s):\n" + taskList + "\n";
    }

    /**
     * Displays the total amount of tasks in the taskList.
     *
     * @param taskList Tasklist to be used.
     * @return String containing the total number of tasks.
     */
    public String showTotalTasks(TaskList taskList) {
        return "now u have " + taskList.getSize() + " task(s)!\n";
    }

    /**
     * Displays the matching tasks for the user.
     *
     * @param taskList Tasklist to be displayed.
     * @return String showing tasks with matching keyword.
     */
    public String showMatchingTasks(TaskList taskList) {
        int size = taskList.getSize();
        if (size > 0) {
            return "here are the " + size + " matching task(s): \n" + taskList + "\n";
        } else {
           return "there are no tasks matching this keyword!\n";
        }
    }
    
    /**
     * Displays an error message.
     *
     * @param message Message to be displayed.
     * @return String with error message.
     */
    public String showError(String message) {
        return "error! " + message + "\n";
    }



}
