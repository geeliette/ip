import java.util.Scanner;
import java.util.ArrayList;

public class Fleur {
    private static ArrayList<Task> tasks = new ArrayList<>(); // arraylist of tasks
    private static int count = 0; // number of tasks
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Fleur fleur = new Fleur();
        fleur.run();
    }

    private void run() {
        System.out.println("'Allo! I'm Fleur.");
        System.out.println("Tell me what you need to do, s'il vous plaît.");

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            try {
                if (str.equalsIgnoreCase("bye")) {
                    System.out.println("Au revoir, 'ope to see you again soon!");
                    return;
                } else if (str.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (str.startsWith("mark")) {
                    int index = Integer.parseInt(str.substring(5)) - 1;
                    markDone(index);
                } else if (str.startsWith("unmark")) {
                    int index = Integer.parseInt(str.substring(7)) - 1;
                    markUndone(index);
                } else if (str.startsWith("todo")) {
                    addToDo(str);
                } else if (str.startsWith("deadline")) {
                    addDeadline(str);
                } else if (str.startsWith("event")) {
                    addEvent(str);
                } else {
                    throw new FleurInvalidCommandException();
                }
            } catch (FleurException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("You 'ave non tasks in your list.");
        } else {
            System.out.println("'Ere are all ze tasks in your list:");
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        }
    }

    private void markDone(int index) {
        tasks.get(index).markAsDone();
        System.out.println("Enchanté! I 'ave marked zis task as done:");
        System.out.println(tasks.get(index).toString());
    }

    private void markUndone(int index) {
        tasks.get(index).markAsUndone();
        System.out.println("Zut! I 'ave marked zis task as not done:");
        System.out.println(tasks.get(index).toString());
    }

    private void addToDo(String str) throws FleurMissingDetailsException {
        try {
            String desc = str.substring(5);
            Task t = new ToDo(desc);
            tasks.add(t);
            count++;
            System.out.println("Bah, oui! I 'ave added zis todo task to your list:");
            System.out.println(t.toString());
            System.out.println("Now you 'ave " + count + " task(s) in your list.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new FleurMissingDetailsException();
        }
    }

    private void addDeadline(String str) throws FleurMissingDetailsException {
        try {
            String desc = str.substring(9).split("/by")[0];
            String date = str.substring(9).split("/by")[1];
            Task t = new Deadline(desc, date);
            tasks.add(t);
            count++;
            System.out.println("Bah, oui! I 'ave added zis deadline task to your list:");
            System.out.println(t.toString());
            System.out.println("Now you 'ave " + count + " task(s) in your list.");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new FleurMissingDetailsException();
        }
    }

    private void addEvent(String str) throws FleurMissingDetailsException {
        try {
            String[] arr = str.substring(6).split("/from");
            String desc = arr[0];
            String from = arr[1].split("/to")[0];
            String to = arr[1].split("/to")[1];
            Task t = new Event(desc, from, to);
            tasks.add(t);
            count++;
            System.out.println("Bah, oui! I 'ave added zis event task to your list:");
            System.out.println(t.toString());
            System.out.println("Now you 'ave " + count + " task(s) in your list.");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new FleurMissingDetailsException();
        }
    }

}
