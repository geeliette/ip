package fleur.commands;

import fleur.tasks.TaskList;
import fleur.tasks.Task;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(String input) {
        this.index = Integer.parseInt(input.substring(7)) - 1;;
    }

    @Override
    public String execute(TaskList tasks) {
        StringBuilder result = new StringBuilder();
        Task deletedTask = tasks.getTask(index);
        result.append("D'accord, I 'ave removed zis task from your list:\n");
        result.append(deletedTask);
        result.append("\n");
        tasks.deleteTask(index);
        result.append("Now you 'ave ").append(tasks.size()).append(" task(s) in your list.");
        return result.toString();
    }

}
