package fleur.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a deadline task with a description and a specific due date.
 *
 */
public class Deadline extends Task {

    protected LocalDate by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a new deadline task with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task in "dd/MM/yyyy" format.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public void edit(String input) {
        String newDescription = input.split("/by")[0];
        String dueDate = input.split("/by")[1].trim();
        LocalDate newBy = LocalDate.parse(dueDate, INPUT_FORMAT);
        this.description = newDescription;
        this.by = newBy;
    }

    @Override
    public String getTaskType() {
        return "deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by.format(OUTPUT_FORMAT) + ")";
    }
}