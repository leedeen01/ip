package mavis.task;

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
    String name;
    
    /**
     * The completion status of the task. True if the task is done, false otherwise.
     */
    Boolean done;

    /**
     * Constructor to create a new task with a given name.
     * By default, the task is marked as not done.
     * 
     * @param name The name of the task.
     * @param done The completion status of the task. True if the task is done, false otherwise.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
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
        return done;
    }

    /**
     * Sets the completion status of the task.
     * 
     * @param done True to mark the task as done, false to mark it as not done.
     */
    public void setDone(Boolean done) {
        this.done = done;
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
}
