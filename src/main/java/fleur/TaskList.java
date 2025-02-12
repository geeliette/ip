package fleur;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks with operations to add, delete,
 * get, mark and display tasks.
 *
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with the given list of tasks.
     *
     * @param tasks The intiial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) { // add throw exception for empty tasklist later
        this.tasks = tasks;
    }

    /**
     * Constructs a new, empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a new task into the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The task to be deleted.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index The index of the task to be returned.
     * @return Task.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Marks task at given index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks task at given index as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void markUndone(int index) {
        this.tasks.get(index).markAsUndone();
    }

    /**
     * Displays and numbers all the tasks in the list.
     * If list is empty, a message indicating that the user has no tasks will be displayed.
     */
    public void listTasks() {
        if (this.tasks.isEmpty()) {
            System.out.println("You 'ave non tasks in your list.");
        } else {
            System.out.println("'Ere are all ze tasks in your list:");
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println((i + 1) + "." + this.tasks.get(i).toString());
            }
        }
    }

    public TaskList findTask(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

}
