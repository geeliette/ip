package fleur.commands;

import fleur.tasks.TaskList;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(String input) {
        this.index = Integer.parseInt(input.substring(5)) - 1;;
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.markDone(this.index);
        StringBuilder result = new StringBuilder();
        result.append("Enchanté! I 'ave marked zis task as done:\n");
        result.append(tasks.getTask(this.index));
        return result.toString();
    }
}
