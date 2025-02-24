package fleur.commands;

import fleur.tasks.Task;
import fleur.tasks.Deadline;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditDeadlineCommand extends EditCommand {

    private String input;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public EditDeadlineCommand(String input) {
        super(input);
        this.input = input;
    }

    public Task editDeadline(Deadline task) {
        String description = input.split("/by")[0];
        String dueDate = input.split("/by")[1].trim();
        LocalDate by = LocalDate.parse(dueDate, INPUT_FORMAT);
        task.edit(description, by);
        return task;
    }

}
