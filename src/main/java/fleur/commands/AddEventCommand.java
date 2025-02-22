package fleur.commands;

import fleur.tasks.TaskList;
import fleur.tasks.Task;
import fleur.tasks.Event;
import fleur.exceptions.FleurMissingDetailsException;
import fleur.exceptions.FleurInvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {

    private String input;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AddEventCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) throws FleurMissingDetailsException, FleurInvalidDateException {
        try {
            StringBuilder result = new StringBuilder();
            String[] commandArray = input.substring(6).split("/from");
            String eventDescription = commandArray[0];
            String fromDate = commandArray[1].split("/to")[0].trim();
            String toDate = commandArray[1].split("/to")[1].trim();
            LocalDate dateFrom = LocalDate.parse(fromDate, INPUT_FORMAT);
            LocalDate dateTo = LocalDate.parse(toDate, INPUT_FORMAT);
            Task newEvent = new Event(eventDescription, dateFrom, dateTo);
            tasks.addTask(newEvent);
            result.append("Bah, oui! I 'ave added zis event task to your list:\n");
            result.append(newEvent);
            result.append("\n");
            result.append("Now you 'ave ").append(tasks.size()).append(" task(s) in your list.");
            return result.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new FleurMissingDetailsException();
        } catch (DateTimeParseException e) {
            throw new FleurInvalidDateException();
        }
    }
}
