package fleur.tasks;

/**
 * The ToDo class represents a todo task with a description and completion status.
 *
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "todo";
    }

    public void edit(String newDescription) {
        this.description = newDescription;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

