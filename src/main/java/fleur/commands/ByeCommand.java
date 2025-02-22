package fleur.commands;

import fleur.tasks.TaskList;

public class ByeCommand extends Command {

    /**
     * Displays a goodbye message to the user.
     *
     * @return The output to show to the user.
     */
    @Override
    public String execute(TaskList tasks) {
        return "Au revoir, 'ope to see you again soon!";
    }

    /**
     * Checks if it is an exit command.
     *
     * @return True as Bye is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
