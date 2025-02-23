package fleur.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import fleur.tasks.TaskList;
import fleur.tasks.Task;
import fleur.commands.AddToDoCommand;
import fleur.commands.AddDeadlineCommand;
import fleur.commands.AddEventCommand;
import fleur.exceptions.FleurCorruptFileException;

/**
 * The Storage class handles the reading and writing of task data to a file.
 * This class provides methods to load tasks from the data file and to save current tasks.
 *
 */
public class Storage {

    private static String dataFile;

    /**
     * Constructs a Storage object with the given data file path for saving and loading tasks.
     *
     * @param dataFile The path to the file with stored tasks.
     */
    public Storage(String dataFile) {
        this.dataFile = dataFile;
    }

    /**
     * Loads tasks from the data file at the specified file path.
     * If the file does not exist, an empty arraylist of tasks is returned.
     *
     * @return An ArrayList of tasks from the file.
     * @throws IOException If the file is unable to be read.
     * @throws FleurCorruptFileException If the file has been modified and contains unknown commands,
     */
    public ArrayList<Task> loadData() throws IOException, FleurCorruptFileException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(dataFile);
        if (!file.exists()) {
            return tasks;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String command;
        while ((command = br.readLine()) != null) {
            Task task = switch (command.charAt(1)) {
                case 'T' -> new AddToDoCommand(command).createToDo();
                case 'D' -> new AddDeadlineCommand(command).createDeadline();
                case 'E' -> new AddEventCommand(command).createEvent();
                default -> throw new FleurCorruptFileException();
            };
            if (command.charAt(4) == 'X') {
                task.markAsDone();
            }
            tasks.add(task);
        }
        br.close();
        return tasks;
    }

    /**
     * Saves the given list of tasks to the data file at the file path.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If the list of tasks cannot be saved.
     */
    public void saveData(TaskList tasks) throws IOException {
        try {
            StringBuilder data = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                data.append(task.toString()).append("\n");
            }
            FileWriter fw = new FileWriter(dataFile, false);
            fw.write(data.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Mince! Zere was an error when saving your tasks.");
        }
    }

}
