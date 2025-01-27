package mavis.task;

import mavis.MavisException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a task that has a specific due date.
 * It extends the abstract Task class and adds a due date field.
 */
public class Deadline extends Task {

    /**
     * The due date of the task.
     */
    private LocalDateTime dueDate;

    /**
     * Constructs a Deadline with the specified name and due date.
     * The due date should be provided in the format "yyyy-MM-dd HHmm".
     * 
     * @param name    The name of the task. It cannot be empty.
     * @param dueDate The due date of the task in the format "yyyy-MM-dd HHmm". 
     *                It must be a valid date-time string.
     * @throws MavisException If the date format is invalid or if the dueDate is incorrectly formatted.
     */
    public Deadline(String name, String dueDate) throws MavisException {
        super(name, false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.dueDate = LocalDateTime.parse(dueDate, formatter);
        } catch (DateTimeParseException e) {
            throw new MavisException("Invalid date format. Please use yyyy-MM-dd HHmm. Example: task /by 2025-02-10 1800");
        }
    }

    /**
     * Constructs a Deadline object with the specified name, due date, and completion status.
     * The due date is parsed from a string using the ISO_LOCAL_DATE_TIME format.
     * 
     * @param name    The name of the task or deadline.
     * @param dueDate The due date of the task in ISO_LOCAL_DATE_TIME format (e.g., "2025-01-26T15:30:00").
     * @param done    A boolean indicating whether the task is completed (true) or not (false).
     */
    public Deadline(String name, String dueDate, boolean done) {
        super(name, done);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        this.dueDate = LocalDateTime.parse(dueDate, formatter);
    }

    /**
     * Generates a detailed report of the deadline task, including its completion status,
     * name, and due date.
     * 
     * @return A string representation of the deadline task with its details.
     */
    @Override
    public String report() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        Boolean done = super.getDone();
        if (done) {
            return "[D]"  + "[X] " + super.getName() + " (by: " + dueDate.format(formatter) + ")";
        }
        return "[D][ ] " + super.getName() + " (by: " + dueDate.format(formatter) + ")";
    }

    /**
     * Converts the deadline task to a string for saving.
     * 
     * @return A string representing the task, including its completion status.
     */
    @Override
    public String saveTask() {
        Boolean done = super.getDone();
        if (done) {
            return "D"  + "|" + "1" + "|" + super.getName() + "|" + this.dueDate;
        }
        return "D"  + "|" + "0" + "|" + super.getName() + "|" + this.dueDate;
    }
}
