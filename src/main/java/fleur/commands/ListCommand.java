package fleur.commands;

import fleur.tasks.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks) {
        if (tasks.size() == 0) {
            return "You 'ave non tasks in your list.";
        } else {
            StringBuilder result = new StringBuilder();
            result.append("'Ere are all ze tasks in your list:\n");
            result.append(tasks.listTasks());
            return result.toString();
        }
    }

}
