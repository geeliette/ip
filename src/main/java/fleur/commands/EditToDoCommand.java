package fleur.commands;

import fleur.tasks.Task;
import fleur.tasks.ToDo;

public class EditToDoCommand extends EditCommand {

    private String description;

    public EditToDoCommand(String description) {
        super(description);
        this.description = description;
        System.out.println("DEBUG: EditToDoCommand Received Description: " + this.description);
    }

    public Task editToDo(ToDo task) {
        System.out.println("DEBUG: Task Before Edit: " + task);
        System.out.println("DEBUG: New Description: " + description);
        task.edit(description);
        System.out.println("DEBUG: Task After Edit: " + task);
        return task;
    }
}
