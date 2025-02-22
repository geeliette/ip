package fleur;

import org.junit.jupiter.api.Test;

import fleur.tasks.TaskList;
import fleur.tasks.Task;
import fleur.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void addAndDeleteTaskTest() {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("watch lecture");

        taskList.addTask(todo);

        assertEquals(1, taskList.size());

        Task deletedTask = taskList.getTask(0);
        taskList.deleteTask(0);

        assertEquals(todo, deletedTask);
        assertEquals(0, taskList.size());
    }

    @Test
    public void markDoneAndUndoneTest() {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("study");

        taskList.addTask(todo);

        assertFalse(todo.isDone(), "Task should not be done initially.");

        taskList.markDone(0);
        assertTrue(todo.isDone(), "Task should be marked as done.");

        taskList.markUndone(0);
        assertFalse(todo.isDone(), "Task should be marked as not done.");
    }
}