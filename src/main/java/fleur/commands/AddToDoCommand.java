package fleur.commands;

import fleur.tasks.TaskList;
import fleur.tasks.Task;
import fleur.tasks.ToDo;
import fleur.exceptions.FleurMissingDetailsException;

public class AddToDoCommand extends Command {

    private String input;

    public AddToDoCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) throws FleurMissingDetailsException {
        try {
            StringBuilder result = new StringBuilder();
            String description = input.substring(5);
            Task newToDo = new ToDo(description);
            tasks.addTask(newToDo);
            result.append("Bah, oui! I 'ave added zis todo task to your list:\n");
            result.append(newToDo);
            result.append("\n");
            result.append("Now you 'ave ").append(tasks.size()).append(" task(s) in your list.");
            return result.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new FleurMissingDetailsException();
        }
    }

    public Task createToDo() {
        String description = input.substring(7);
        Task newToDo = new ToDo(description);
        return newToDo;
    }

}
