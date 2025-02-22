package fleur.commands;

import fleur.tasks.TaskList;

public class InvalidCommand extends Command {
    private final String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute(TaskList tasks) {
        return errorMessage;
    }
}