package fleur.commands;

import fleur.tasks.TaskList;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(String input) {
        this.index = Integer.parseInt(input.substring(7)) - 1;;
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.markUndone(this.index);
        StringBuilder result = new StringBuilder();
        result.append("Zut! I 'ave marked zis task as not done:\n");
        result.append(tasks.getTask(this.index));
        return result.toString();
    }
}
