import java.util.Scanner;

public class Fleur {
    private static Task[] tasks = new Task[100]; // array of tasks
    private static int count = 0; // number of tasks

    public static void main(String[] args) {
        System.out.println("'Allo! I'm Fleur.");
        System.out.println("Tell me what you need to do, s'il vous plaît.");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            // input is bye
            if (str.equalsIgnoreCase("bye")) {
                System.out.println("Au revoir, 'ope to see you again soon!");
                return;
            // input is list
            } else if (str.equalsIgnoreCase("list")) {
                System.out.println("'Ere are all ze tasks in your list: ");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i].toString());
                }
            // input is mark task as done
            } else if (str.startsWith("mark")) {
                // get task number
                int index = Integer.parseInt(str.substring(5)) - 1;
                tasks[index].markAsDone();
                System.out.println("Enchanté! I 'ave marked zis task as done:");
                System.out.println(tasks[index].toString());
            // input is mark task as undone
            } else if (str.startsWith("unmark")) {
                int index = Integer.parseInt(str.substring(7)) - 1;
                tasks[index].markAsUndone();
                System.out.println("Zut! I 'ave marked zis task as not done:");
                System.out.println(tasks[index].toString());
            // input is task
            } else {
                Task t = new Task(str);
                tasks[count] = t;
                count++;
                System.out.println("added: " + str);
            }
        }
    }
}
