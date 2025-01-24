/**
 * The Task class represents an abstract task with a name and a completion status.
 * This class is meant to be extended by more specific types of tasks.
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
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
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
    public abstract String report();
}

