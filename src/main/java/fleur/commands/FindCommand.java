package fleur.commands;

import fleur.tasks.TaskList;

public class FindCommand extends Command {

    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        String keyword = input.substring(5);
        TaskList matchingTasks = tasks.findTask(keyword);
        if (matchingTasks.size() == 0) {
            return "Zere are no tasks found.";
        } else {
            StringBuilder result = new StringBuilder();
            result.append("'Ere are ze task(s) in your list:\n");
            result.append(matchingTasks.listTasks());
            return result.toString();
        }
    }

}
