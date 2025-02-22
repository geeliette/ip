package fleur.tasks;

/**
 * The Task class represents a generic task with a description and completion status.
 * This class serves as the base class for the types of tasks: ToDo, Deadline and Event.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is marked as undone initially.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, representing its completion status.
     * If the task is done, the status icon is "X".
     * If the task is undone, the status icon is " ".
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task.
     * The string includes the task's status icon and description.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}