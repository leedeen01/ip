package mavis.task;

import mavis.MavisException;

/**
 * The Task class represents a generic task with a name and a completion status.
 * This class serves as a base class intended to be extended by more specific
 * types of tasks that may add additional properties or behavior.
 * It provides basic functionality to set and get the task's name and completion status.
 *
 * Subclasses should implement more specific task-related logic, such as deadlines,
 * priorities, or custom completion conditions.
 */
public abstract class Task {

    /**
     * The name of the task.
     */
    private String name;

    /**
     * The completion status of the task. True if the task is done, false otherwise.
     */
    private Boolean isDone;

    /**
     * Constructor to create a new task with a given name.
     * By default, the task is marked as not done.
     *
     * @param name The name of the task.
     * @param done The completion status of the task. True if the task is done, false otherwise.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public Boolean getDone() {
        return isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone True to mark the task as done, false to mark it as not done.
     */
    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a summary of the task.
     *
     * @return A string description of the task.
     */
    public abstract String report();

    /**
     * Converts the task to a string format suitable for saving to a file.
     *
     * @return A string format of the task.
     */
    public abstract String saveTask();

    public abstract void checkOverlapAnomalies(Task newTask) throws MavisException;
}
