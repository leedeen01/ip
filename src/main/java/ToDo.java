/**
 * The ToDo class represents a simple task without a specific due date or event timeline.
 * It extends the abstract Task class and provides a basic implementation.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the specified name.
     * 
     * @param name The name of the ToDo task.
     */
    public ToDo(String name) {
        super(name);
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
}

