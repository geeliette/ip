package fleur;

import java.util.Scanner;

public class Ui {

    private static Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void welcomeMessage() {
        System.out.println("'Allo! I'm Fleur.");
        System.out.println("Tell me what you need to do, s'il vous plaît.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

}
