package fleur.commands;

import fleur.tasks.TaskList;
import fleur.tasks.Task;
import fleur.tasks.Deadline;
import fleur.exceptions.FleurMissingDetailsException;
import fleur.exceptions.FleurInvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {

    private String input;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) throws FleurMissingDetailsException, FleurInvalidDateException {
        try {
            StringBuilder result = new StringBuilder();
            String deadlineDescription = input.substring(9).split("/by")[0];
            String dueDate = input.substring(9).split("/by")[1].trim();
            LocalDate by = LocalDate.parse(dueDate, INPUT_FORMAT);
            Task newDeadline = new Deadline(deadlineDescription, by);
            tasks.addTask(newDeadline);
            result.append("Bah, oui! I 'ave added zis deadline task to your list:\n");
            result.append(newDeadline);
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
