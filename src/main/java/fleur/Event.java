package fleur;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents an event task with a description, start and end date.
 *
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a new event task with the given description, start and end date.
     *
     * @param description The description of the event task.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from.format(OUTPUT) +
                " to: " + this.to.format(OUTPUT) + ")";
    }
}