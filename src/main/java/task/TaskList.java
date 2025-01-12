package task;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all features related to the tasklist.
 */
public class TaskList {
    /** Tasks to be stored */
    private List<Task> tasks;

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        clear();
    }

    /**
     * Constructor for the TaskList class.
     *
     * @param tasks Tasks to be contained.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Appends List of Task objects to this tasklist.
     *
     * @param lst List to append.
     */
    public void append(List<Task> lst) {
        this.tasks.addAll(lst);
    }

    /**
     * Clears the current list of tasks.
     */
    public void clear() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task to the TaskList.
     *
     * @param index Index of task to be removed.
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Marks a task in the TaskList.
     *
     * @param index Index of task to be marked.
     */
    public void markTask(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Unmarks a task in the TaskList.
     *
     * @param index Index of task to be unmarked.
     */
    public void unmarkTask(int index) {
        this.tasks.get(index).markAsUndone();
    }

    /**
     * Gets a task in the TaskList.
     *
     * @param index Index of task to be retrieved.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets size of the TaskList.
     *
     * @return int Int containing the size.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Formats the tasks for writing to file.
     *
     * @return String containing format for saving.
     */
    public String formatTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < getSize(); i++) {
            stringBuilder.append(getTask(i).formatToSave() + "\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Finds the tasks with the matching description
     *
     * @param description to find tasks.
     * @return Tasklist containing the tasks.
     */
    public TaskList findTasks(String description) {
        List<Task> foundTasks = new ArrayList<>();

        for(Task task : tasks) {
            if(task.contains(description)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String str = "";
        for(int i = 1; i <= this.getSize(); i++) {
            Task task = this.getTask(i - 1);
            str += i + ". " + task.toString() + "\n";
        }
        return str;
    }
}
