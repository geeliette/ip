import java.util.Scanner;

public class Fleur {
    private static String[] list = new String[100];
    private static int count = 0;

    public static void main(String[] args) {
        System.out.println("'Allo! I'm Fleur.");
        System.out.println("Tell me what you need to do, s'il vous plaît.");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                System.out.println("Au revoir, 'ope to see you again soon!");
                return;
            } else if (str.equalsIgnoreCase("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
            } else {
                list[count] = str;
                count++;
                System.out.println("added: " + str);
            }
        }
    }
}
