package fleur;

import java.io.IOException;

import fleur.storage.Storage;
import fleur.tasks.TaskList;
import fleur.ui.Ui;
import fleur.exceptions.FleurException;
import fleur.parser.Parser;

/**
 * The Fleur class represents the main application for the chatbot Fleur.
 *
 * This class manages loading tasks from a data file, processing user commands,
 * and saving tasks to the data file.
 *
 */
public class Fleur {

    private Storage storage;
    private fleur.tasks.TaskList tasks;
    private static Ui ui;

    /**
     * Constructs an instance of the Fleur application with specified data file.
     *
     * @param filePath The path to the data file that stores tasks.
     */
    public Fleur(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FleurException | IOException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts Fleur's main loop.
     * A welcome message is first displayed before processing user
     * input until the exit command is given and tasks are saved.
     *
     * @throws IOException If data file could not be saved.
     */
    public void run() throws IOException {
        ui.showWelcomeMessage();
        Parser parser = new Parser(this.tasks);
        boolean isExit = false;
        while (!isExit) {
            String str = ui.readCommand();
            parser.parse(str);
            isExit = parser.isExit();
        }
        storage.saveData(this.tasks);
    }

    /**
     * Starts the Fleur bot application.
     * Creates a new Fleur instance with the data file and
     * runs the application.
     *
     * @param args Command line arguments (not used).
     * @throws IOException If data file could not be saved.
     */
    public static void main(String[] args) throws IOException {
        new Fleur("./src/main/data/fleur.txt").run();
    }

}