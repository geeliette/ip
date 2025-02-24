package fleur.commands;

import fleur.tasks.TaskList;
import fleur.tasks.Task;
import fleur.tasks.ToDo;
import fleur.tasks.Deadline;
import fleur.tasks.Event;
import fleur.exceptions.FleurException;
import fleur.exceptions.FleurInvalidCommandException;

public class EditCommand extends Command {

    private String input;

    public EditCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks) throws FleurException {
        String[] parts = input.split(" ", 3);
        String description = parts[2];
        int index = Integer.parseInt(parts[1]) - 1;
        assert index >= 0 : "Task number must be greater than 0.";
        assert index <= tasks.size() : "Task number must be less than or equal to number of tasks";
        Task oldTask = tasks.getTask(index);
        String taskType = oldTask.getTaskType();

        StringBuilder result = new StringBuilder();
        result.append("D'accord, your task has been edited:\n");

        Task editedTask = null;
        switch (taskType) {
        case "todo":
            ToDo oldToDo = (ToDo) oldTask;
            EditToDoCommand editToDoCommand = new EditToDoCommand(description);
            editedTask = editToDoCommand.editToDo(oldToDo);
            break;
        case "deadline":
            Deadline oldDeadline = (Deadline) oldTask;
            EditDeadlineCommand editDeadlineCommand = new EditDeadlineCommand(description);
            editedTask = editDeadlineCommand.editDeadline(oldDeadline);
            break;
        case "event":
            Event oldEvent = (Event) oldTask;
            EditEventCommand editEventCommand = new EditEventCommand(description);
            editedTask = editEventCommand.editEvent(oldEvent);
            break;
        default:
            throw new FleurInvalidCommandException();
        }
        result.append(editedTask);
        return result.toString();
    }
}
