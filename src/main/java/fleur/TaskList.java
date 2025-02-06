package fleur;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) { // add throw exception for empty tasklist later
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public void markDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    public void markUndone(int index) {
        this.tasks.get(index).markAsUndone();
    }

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

}
