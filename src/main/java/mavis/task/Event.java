package mavis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import mavis.MavisException;

/**
 * The Event class represents a task that has a specific start and end date.
 * It extends the abstract Task class, adding date-related details.
 */
public class Event extends Task {

    /**
     * The start date of the event.
     */
    private LocalDateTime startDate;

    /**
     * The end date of the event.
     */
    private LocalDateTime endDate;

    /**
     * Constructs an Event with the specified name, start date, and end date.
     * The dates should be provided in the format "yyyy-MM-dd HHmm".
     *
     * @param name      The name of the event. It cannot be empty.
     * @param startDate The start date of the event in the format "yyyy-MM-dd HHmm".
     *                  It must be a valid date-time string.
     * @param endDate   The end date of the event in the format "yyyy-MM-dd HHmm".
     *                  It must be a valid date-time string.
     * @throws MavisException If the date format is invalid, or if the startDate or endDate is incorrectly formatted.
     */
    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) throws MavisException {
        super(name, false);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs an Event object with the specified name, start and end dates, and completion status.
     * Both the start and end dates are parsed from strings using the ISO_LOCAL_DATE_TIME format.
     *
     * @param name      The name of the event.
     * @param startDate The start date of the event in ISO_LOCAL_DATE_TIME format (e.g., "2025-01-26T09:00:00").
     * @param endDate   The end date of the event in ISO_LOCAL_DATE_TIME format (e.g., "2025-01-26T17:00:00").
     * @param done      A boolean indicating whether the event is completed (true) or not (false).
     */
    public Event(String name, String startDate, String endDate, boolean done) {
        super(name, done);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        this.startDate = LocalDateTime.parse(startDate, formatter);
        this.endDate = LocalDateTime.parse(endDate, formatter);
    }

    /**
     * Generates a detailed report of the event, including its completion status,
     * name, and date range.
     *
     * @return A string representation of the event with its details.
     */
    @Override
    public String report() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        Boolean done = super.getDone();
        if (done) {
            return "[E]" + "[X] " + super.getName()
                + " (from: " + startDate.format(formatter) + " -> to: " + endDate.format(formatter) + ")";
        }
        return "[E][ ] " + super.getName()
            + " (from: " + startDate.format(formatter) + " -> to: " + endDate.format(formatter) + ")";
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
            return "E" + "|" + "1" + "|" + super.getName() + "|" + startDate + "|" + endDate;
        }
        return "E" + "|" + "0" + "|" + super.getName() + "|" + startDate + "|" + endDate;
    }

    /**
     * Checks for date and time overlap anomalies between this event and the given task.
     * If the task has the same name and overlaps with this event's time frame,
     * a MavisException will be thrown.
     *
     * @param newTask The task to check for overlap anomalies.
     * @throws MavisException If a task with the same name and overlapping time frame exists.
     */
    @Override
    public void checkOverlapAnomalies(Task newTask) throws MavisException {
        if (this.getDone()) {
            return;
        }
        if (this.getName().equals(newTask.getName()) && newTask instanceof Event) {
            Event newEvent = (Event) newTask;
            if (this.startDate.isEqual(newEvent.startDate)
                || this.endDate.isEqual(newEvent.endDate)
                || (this.startDate.isBefore(newEvent.startDate) && this.endDate.isAfter(newEvent.endDate))
                || (this.startDate.isAfter(newEvent.startDate) && this.endDate.isBefore(newEvent.endDate))
                || (this.startDate.isBefore(newEvent.startDate) && this.endDate.isAfter(newEvent.startDate))
                || (this.startDate.isAfter(newEvent.startDate) && this.startDate.isBefore(newEvent.endDate))) {
                throw new MavisException("This task " + newTask.report() + " overlaps with the existing event.");
            }
        }
    }

}
