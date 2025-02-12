package fleur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

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
    protected ArrayList<Task> loadData() throws IOException, FleurCorruptFileException {
        ArrayList<Task> tasks = new ArrayList<>();
        DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("MMM dd yyyy");
        File file = new File(dataFile);
        if (!file.exists()) {
            return tasks;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        while ((str = br.readLine()) != null) {
            Task t = null;
            switch (str.charAt(1)) {
            case 'T':
                t = new ToDo(str.substring(7));
                break;
            case 'D':
                String desc = str.substring(7).split("\\(by: ")[0];
                String date = str.substring(7).split("\\(by: ")[1].replace(")", "");
                LocalDate by = LocalDate.parse(date, INPUT);
                t = new Deadline(desc, by);
                break;
            case 'E':
                String[] arr = str.substring(7).split("\\(from: ");
                String description = arr[0];
                String from = arr[1].split("to: ")[0].trim();
                String to = arr[1].split("to: ")[1].replace(")", "");
                LocalDate dateFrom = LocalDate.parse(from, INPUT);
                LocalDate dateTo = LocalDate.parse(to, INPUT);
                t = new Event(description, dateFrom, dateTo);
                break;
            default:
                throw new FleurCorruptFileException();
            }
            if (str.charAt(4) == 'X') {
                t.markAsDone();
            }
            tasks.add(t);
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
    protected void saveData(TaskList tasks) throws IOException {
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
