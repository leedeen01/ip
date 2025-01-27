package mavis.task;

/**
 * The ToDo class represents a simple task without a specific due date or event timeline.
 * It extends the abstract Task class and provides a basic implementation.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified name.
     * The task is initially marked as not done.
     * 
     * @param name The name of the ToDo task.
     */
    public ToDo(String name) {
        super(name, false);
    }

    /**
     * Constructs a ToDo task with the specified name and completion status.
     * 
     * @param name The name of the ToDo task.
     * @param done The completion status of the task. True if the task is done, false otherwise.
     */
    public ToDo(String name, boolean done) {
        super(name, done);
    }

    /**
     * Generates a detailed report of the ToDo task, including its completion status and name.
     * 
     * @return A string representation of the ToDo task with its details.
     */
    @Override
    public String report() {
        Boolean done = super.getDone();
        if (done) {
            return "[T]"  + "[X] " + super.getName();
        }
        return "[T][ ] " + super.getName();
    }

    /**
     * Converts the to-do task to a string for saving.
     * 
     * @return A string representing the task, including its completion status.
     */
    @Override
    public String saveTask() {
        Boolean done = super.getDone();
        if (done) {
            return "T"  + "|" + "1" + "|" + super.getName();
        }
        return "T"  + "|" + "0" + "|" + super.getName();
    }
}
