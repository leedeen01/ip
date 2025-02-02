package mavis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    public Event(String name, String startDate, String endDate) throws MavisException {
        super(name, false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.startDate = LocalDateTime.parse(startDate, formatter);
            this.endDate = LocalDateTime.parse(endDate, formatter);
        } catch (DateTimeParseException e) {
            throw new MavisException("Invalid date format. Please use yyyy-MM-dd HHmm. "
            + "Example: task /from 2025-02-10 0900 to 2025-02-10 1700");
        }
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
}
