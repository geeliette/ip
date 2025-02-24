package fleur.ui;

import java.util.Scanner;

/**
 * The Ui class handles interactions with the user by reading commands and displaying messages.
 * This class provides methods to display a welcome message and to read user input.
 *
 */
public class Ui {

    private static Scanner scanner;

    /**
     * Constructs a new Ui instance and initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**\
     * Displays a welcome message to the user when the program starts.
     */
    public String showWelcomeMessage() {
        return "'Allo! I'm Fleur. Tell me what you need to do, s'il vous plaît.";
    }

    /**
     * Reads a command from the user input.
     *
     * @return The user command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

}
