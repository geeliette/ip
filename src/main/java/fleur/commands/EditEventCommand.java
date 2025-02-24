package fleur.commands;

import fleur.tasks.Task;
import fleur.tasks.Event;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditEventCommand extends EditCommand {

    private String input;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public EditEventCommand(String input) {
        super(input);
        this.input = input;
    }

    public Task editEvent(Event task) {
        String[] commandArray = input.split("/from");
        String description = commandArray[0];
        String fromDate = commandArray[1].split("/to")[0].trim();
        String toDate = commandArray[1].split("/to")[1].trim();
        LocalDate dateFrom = LocalDate.parse(fromDate, INPUT_FORMAT);
        LocalDate dateTo = LocalDate.parse(toDate, INPUT_FORMAT);
        task.edit(description, dateFrom, dateTo);
        return task;
    }
}
