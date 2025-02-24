package fleur.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents an event task with a description, start and end date.
 *
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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

    public void edit(String input) {
        String[] commandArray = input.split("/from");
        String newDescription = commandArray[0];
        String fromDate = commandArray[1].split("/to")[0].trim();
        String toDate = commandArray[1].split("/to")[1].trim();
        LocalDate newFrom = LocalDate.parse(fromDate, INPUT_FORMAT);
        LocalDate newTo = LocalDate.parse(toDate, INPUT_FORMAT);
        this.description = newDescription;
        this.from = newFrom;
        this.to = newTo;
    }

    @Override
    public String getTaskType() {
        return "event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from.format(OUTPUT) +
                " to: " + this.to.format(OUTPUT) + ")";
    }
}