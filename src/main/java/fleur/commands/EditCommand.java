package fleur.commands;

import fleur.tasks.TaskList;
import fleur.tasks.Task;
import fleur.exceptions.FleurException;

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
        Task task = tasks.getTask(index);

        StringBuilder result = new StringBuilder();
        result.append("D'accord, your task has been edited:\n");
        task.edit(description);
        result.append(task);
        return result.toString();
    }
}
