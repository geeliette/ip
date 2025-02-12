package fleur;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private TaskList taskList;
    private boolean isExit;
    private static final DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.isExit = false;
    }

    public void parse(String command) {
        try {
            if (command.equalsIgnoreCase("bye")) {
                exit();
            } else if (command.equalsIgnoreCase("list")) {
                this.taskList.listTasks();
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.substring(5)) - 1;
                this.taskList.markDone(index);
                System.out.println("Enchanté! I 'ave marked zis task as done:");
                System.out.println(this.taskList.getTask(index).toString());
            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(command.substring(7)) - 1;
                this.taskList.markUndone(index);
                System.out.println("Zut! I 'ave marked zis task as not done:");
                System.out.println(this.taskList.getTask(index).toString());
            } else if (command.startsWith("todo")) {
                addToDo(command);
            } else if (command.startsWith("deadline")) {
                addDeadline(command);
            } else if (command.startsWith("event")) {
                addEvent(command);
            } else if (command.startsWith("delete")) {
                int index = Integer.parseInt(command.substring(7)) - 1;
                Task deletedTask = this.taskList.getTask(index);
                System.out.println("D'accord, I 'ave removed zis task from your list:");
                System.out.println(deletedTask.toString());
                this.taskList.deleteTask(index);
                System.out.println("Now you 'ave " + this.taskList.size() + " task(s) in your list.");
            } else {
                throw new FleurInvalidCommandException();
            }
        } catch (FleurException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

    private void exit() {
        System.out.println("Au revoir, 'ope to see you again soon!");
        this.isExit = true;
    }

    private void addToDo(String command) throws FleurMissingDetailsException {
        try {
            String description = command.substring(5);
            Task newToDo = new ToDo(description);
            this.taskList.addTask(newToDo);
            System.out.println("Bah, oui! I 'ave added zis todo task to your list:");
            System.out.println(newToDo.toString());
            System.out.println("Now you 'ave " + this.taskList.size() + " task(s) in your list.");
        } catch (IndexOutOfBoundsException e) {
            throw new FleurMissingDetailsException();
        }
    }

    private void addDeadline(String command) throws FleurMissingDetailsException, FleurInvalidDateException {
        try {
            String deadlineDescription = command.substring(9).split("/by")[0];
            String dueDate = command.substring(9).split("/by")[1].trim();
            java.time.LocalDate by = java.time.LocalDate.parse(dueDate, INPUT);
            Task newDeadline = new Deadline(deadlineDescription, by);
            this.taskList.addTask(newDeadline);
            System.out.println("Bah, oui! I 'ave added zis deadline task to your list:");
            System.out.println(newDeadline.toString());
            System.out.println("Now you 'ave " + this.taskList.size() + " task(s) in your list.");
        } catch (IndexOutOfBoundsException e) {
            throw new FleurMissingDetailsException();
        } catch (java.time.format.DateTimeParseException e) {
            throw new FleurInvalidDateException();
        }
    }

    private void addEvent(String command) throws FleurMissingDetailsException, FleurInvalidDateException {
        try {
            String[] commandArray = command.substring(6).split("/from");
            String eventDescription = commandArray[0];
            String fromDate = commandArray[1].split("/to")[0].trim();
            String toDate = commandArray[1].split("/to")[1].trim();
            java.time.LocalDate dateFrom = java.time.LocalDate.parse(fromDate, INPUT);
            java.time.LocalDate dateTo = java.time.LocalDate.parse(toDate, INPUT);
            Task newEvent = new Event(eventDescription, dateFrom, dateTo);
            this.taskList.addTask(newEvent);
            System.out.println("Bah, oui! I 'ave added zis event task to your list:");
            System.out.println(newEvent.toString());
            System.out.println("Now you 'ave " + this.taskList.size() + " task(s) in your list.");
        } catch (IndexOutOfBoundsException e) {
            throw new FleurMissingDetailsException();
        } catch (java.time.format.DateTimeParseException e) {
            throw new FleurInvalidDateException();
        }
    }

}

