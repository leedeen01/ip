/**
 * The Event class represents a task that has a specific start and end date.
 * It extends the abstract Task class, adding date-related details.
 */
public class Event extends Task {
    /**
     * The start date of the event.
     */
    private String startDate;
    /**
     * The end date of the event.
     */
    private String endDate;

    /**
     * Constructs an Event with the specified name, start date, and end date.
     * 
     * @param name      The name of the event.
     * @param startDate The start date of the event.
     * @param endDate   The end date of the event.
     */
    public Event(String name, String startDate, String endDate) {
        super(name, false);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String name, String startDate, String endDate, boolean done) {
        super(name, done);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Generates a detailed report of the event, including its completion status,
     * name, and date range.
     * 
     * @return A string representation of the event with its details.
     */
    @Override
    public String report() {
        Boolean done = super.getDone();
        if (done) {
            return "[E]"  + "[X] " + super.getName() + " (" + startDate + " " + endDate + ")";
        }
        return "[E][ ] " + super.getName() + " (" + startDate + " " + endDate + ")";
    }

    /**
     * Converts the event task to a string for saving.
     * 
     * @return A string representing the task, including its completion status.
     */
    @Override
    public String saveTask() {
        Boolean done = super.getDone();
        if (done) {
            return "E"  + " " + "1" + " " + super.getName() + " " + startDate + " " + endDate;
        }
        return "E"  + " " + "0" + " " + super.getName() + " " + startDate + " " + endDate;
    }
}

