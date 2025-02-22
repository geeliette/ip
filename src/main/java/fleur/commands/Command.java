package fleur.commands;

import fleur.tasks.TaskList;
import fleur.exceptions.FleurException;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {

    public abstract String execute(TaskList tasks) throws FleurException;

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
