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

    public void parse(String str) {
        try {
            if (str.equalsIgnoreCase("bye")) {
                exit();
            } else if (str.equalsIgnoreCase("list")) {
                this.taskList.listTasks();
            } else if (str.startsWith("mark")) {
                int index = Integer.parseInt(str.substring(5)) - 1;
                this.taskList.markDone(index);
                System.out.println("Enchanté! I 'ave marked zis task as done:");
                System.out.println(this.taskList.getTask(index).toString());
            } else if (str.startsWith("unmark")) {
                int index = Integer.parseInt(str.substring(7)) - 1;
                this.taskList.markUndone(index);
                System.out.println("Zut! I 'ave marked zis task as not done:");
                System.out.println(this.taskList.getTask(index).toString());
            } else if (str.startsWith("todo")) {
                addToDo(str);
            } else if (str.startsWith("deadline")) {
                addDeadline(str);
            } else if (str.startsWith("event")) {
                addEvent(str);
            } else if (str.startsWith("delete")) {
                int index = Integer.parseInt(str.substring(7)) - 1;
                Task deletedTask = this.taskList.getTask(index);
                System.out.println("D'accord, I 'ave removed zis task from your list:");
                System.out.println(deletedTask.toString());
                this.taskList.deleteTask(index);
                System.out.println("Now you 'ave " + this.taskList.size() + " task(s) in your list.");
            } else if (str.startsWith("find")){
                String keyword = str.substring(5);
                TaskList matchingTasks = this.taskList.findTask(keyword);
                if (matchingTasks.size() == 0) {
                    System.out.println("Zere are no tasks found.");
                } else {
                    System.out.println("'Ere are ze task(s) in your list:");
                    for (int i = 0; i < matchingTasks.size(); i++) {
                        System.out.println((i + 1) + "." + matchingTasks.getTask(i).toString());
                    }
                }
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

    private void addToDo(String str) throws FleurMissingDetailsException {
        try {
            String desc = str.substring(5);
            Task t = new ToDo(desc);
            this.taskList.addTask(t);
            System.out.println("Bah, oui! I 'ave added zis todo task to your list:");
            System.out.println(t.toString());
            System.out.println("Now you 'ave " + this.taskList.size() + " task(s) in your list.");
        } catch (IndexOutOfBoundsException e) {
            throw new FleurMissingDetailsException();
        }
    }

    private void addDeadline(String str) throws FleurMissingDetailsException, FleurInvalidDateException {
        try {
            String desc = str.substring(9).split("/by")[0];
            String date = str.substring(9).split("/by")[1].trim();
            LocalDate by = LocalDate.parse(date, INPUT);
            Task t = new Deadline(desc, by);
            this.taskList.addTask(t);
            System.out.println("Bah, oui! I 'ave added zis deadline task to your list:");
            System.out.println(t.toString());
            System.out.println("Now you 'ave " + this.taskList.size() + " task(s) in your list.");
        } catch (IndexOutOfBoundsException e) {
            throw new FleurMissingDetailsException();
        } catch (DateTimeParseException e) {
            throw new FleurInvalidDateException();
        }
    }

    private void addEvent(String str) throws FleurMissingDetailsException, FleurInvalidDateException {
        try {
            String[] arr = str.substring(6).split("/from");
            String desc = arr[0];
            String from = arr[1].split("/to")[0].trim();
            String to = arr[1].split("/to")[1].trim();
            LocalDate dateFrom = LocalDate.parse(from, INPUT);
            LocalDate dateTo = LocalDate.parse(to, INPUT);
            Task t = new Event(desc, dateFrom, dateTo);
            this.taskList.addTask(t);
            System.out.println("Bah, oui! I 'ave added zis event task to your list:");
            System.out.println(t.toString());
            System.out.println("Now you 'ave " + this.taskList.size() + " task(s) in your list.");
        } catch (IndexOutOfBoundsException e) {
            throw new FleurMissingDetailsException();
        } catch (DateTimeParseException e) {
            throw new FleurInvalidDateException();
        }
    }

}

