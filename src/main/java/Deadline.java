/**
 * The Deadline class represents a task that has a specific due date.
 * It extends the abstract Task class and adds a due date field.
 */
public class Deadline extends Task {
    /**
     * The due date of the task.
     */
    private String dueDate;
    /**
     * Constructs a Deadline with the specified name and due date.
     * 
     * @param name    The name of the task.
     * @param dueDate The due date of the task.
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }
    
    /**
     * Generates a detailed report of the deadline task, including its completion status,
     * name, and due date.
     * 
     * @return A string representation of the deadline task with its details.
     */
    @Override
    public String report() {
        Boolean done = super.getDone();
        if (done) {
            return "[D]"  + "[X] " + super.getName() + " (" + dueDate + ")";
        }
        return "[D][ ] " + super.getName() + " (" + dueDate + ")";
    }
}

