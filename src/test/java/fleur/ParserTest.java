package fleur;

import fleur.tasks.TaskList;
import fleur.tasks.Task;
import fleur.tasks.Deadline;
import fleur.parser.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void deadlineCommandTest() {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        String input = "deadline return book /by 04/05/2025";

        parser.parse(input);

        assertEquals(1, taskList.size(), "There should be 1 task after parsing a deadline command.");
        Task deadlineTask = taskList.getTask(0);
        assertTrue(deadlineTask instanceof Deadline, "The task should be a deadline task.");

        String expectedOutput = "[D][ ] return book (by: May 04 2025)";
        assertEquals(expectedOutput, deadlineTask.toString(), "The deadline task output should match the expected format.");
    }

    @Test
    public void testParseByeCommandSetsExitFlag() {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        String input = "bye";

        parser.parse(input);

        assertTrue(parser.isExit(), "Parsing 'bye' should set the exit flag to true.");
    }
}