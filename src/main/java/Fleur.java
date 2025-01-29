import java.util.Scanner;

public class Fleur {
    public static void main(String[] args) {
        System.out.println("'Allo! I'm Fleur.");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                System.out.println("Au revoir, 'ope to see you again soon!");
                return;
            } else {
                System.out.println(str);
            }
        }
    }
}
