package fleur;

import java.io.IOException;

import fleur.storage.Storage;
import fleur.tasks.TaskList;
import fleur.ui.Ui;
import fleur.parser.Parser;
import fleur.commands.Command;

import fleur.exceptions.FleurException;

/**
 * The Fleur class represents the main application for the chatbot Fleur.
 *
 * This class manages loading tasks from a data file, processing user commands,
 * and saving tasks to the data file.
 *
 */
public class Fleur {

    private Storage storage;
    private TaskList tasks;
    private static Ui ui;
    private static final String FILEPATH = "./src/main/data/fleur.txt";

    /**
     * Constructs an instance of the Fleur application with specified data file.
     */
    public Fleur() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (FleurException | IOException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
        ui.showWelcomeMessage();
    }

    /**
     * Takes in user input and returns a string response.
     *
     * @param input The user input.
     * @return The response by the bot in the form of String.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser(this.tasks);
            Command command = parser.parse(input);
            return command.execute(this.tasks);
        } catch (FleurException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks if the user input is an exit command.
     *
     * @param input User input.
     * @return True if the user input is an exit command, false otherwise.
     */
    public boolean isExit(String input) {
        Parser parser = new Parser(this.tasks);
        Command command = parser.parse(input);
        return command.isExit();
    }

    /**
     * Saves tasks to data file when the application exits.
     */
    public void saveOnExit() throws IOException {
        this.storage.saveData(this.tasks);
    }

}