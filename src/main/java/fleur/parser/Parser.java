package fleur.parser;

import fleur.commands.Command;
import fleur.commands.ByeCommand;
import fleur.commands.ListCommand;
import fleur.commands.MarkCommand;
import fleur.commands.UnmarkCommand;
import fleur.commands.AddToDoCommand;
import fleur.commands.AddDeadlineCommand;
import fleur.commands.AddEventCommand;
import fleur.commands.DeleteCommand;
import fleur.commands.FindCommand;
import fleur.commands.InvalidCommand;
import fleur.tasks.TaskList;
import fleur.exceptions.FleurException;
import fleur.exceptions.FleurInvalidCommandException;

/**
 * The Parser class processes and performs actions based on user input.
 * This class interprets commands such as "bye", "list", "mark", "unmark", "todo", "deadline",
 * "event" and "delete" and invokes the appropriate methods based on the given command.
 * If given command is invalid, this class throws an exception.
 *
 */
public class Parser {

    private TaskList taskList;
    private boolean isExit;

    /**
     * Constructs a new Parser with the given TaskList.
     * By default, the user input is not the exit command initially.
     *
     * @param taskList The list of tasks to be acted upon based on user input.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.isExit = false;
    }

    /**
     * Parses the user command and performs the corresponding action using the relevant method.
     *
     * @param input The user input.
     */

    public Command parse(String input) {
        String command = input.split(" ")[0];

        try {
            return switch (command) {
            case "bye" -> new ByeCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(input);
            case "unmark" -> new UnmarkCommand(input);
            case "todo" -> new AddToDoCommand(input);
            case "deadline" -> new AddDeadlineCommand(input);
            case "event" -> new AddEventCommand(input);
            case "delete" -> new DeleteCommand(input);
            case "find" -> new FindCommand(input);
            default -> throw new FleurInvalidCommandException();
            };
        } catch (FleurException e) {
            return new InvalidCommand(e.getMessage());
        }

    }

    /**
     * Returns whether the exit command has been given.
     *
     * @return Whether user input is exit command.
     */
    public boolean isExit() {
        return this.isExit;
    }

}

