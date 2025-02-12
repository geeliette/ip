package fleur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

public class Storage {

    private static String dataFile;

    public Storage(String dataFile) {
        this.dataFile = dataFile;
    }

    public ArrayList<Task> loadData() throws IOException, FleurCorruptFileException {
        ArrayList<Task> tasks = new ArrayList<>();
        DateTimeFormatter input = DateTimeFormatter.ofPattern("MMM dd yyyy");
        File file = new File(dataFile);
        if (!file.exists()) {
            return tasks;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String command;
        while ((command = br.readLine()) != null) {
            Task task = null;
            switch (command.charAt(1)) {
            case 'T':
                task = new ToDo(command.substring(7));
                break;
            case 'D':
                String deadlineDescription = command.substring(7).split("\\(by: ")[0];
                String dueDate = command.substring(7).split("\\(by: ")[1].replace(")", "");
                LocalDate by = LocalDate.parse(dueDate, input);
                task = new Deadline(deadlineDescription, by);
                break;
            case 'E':
                String[] commandArray = command.substring(7).split("\\(from: ");
                String eventDescription = commandArray[0];
                String fromDate = commandArray[1].split("to: ")[0].trim();
                String toDate = commandArray[1].split("to: ")[1].replace(")", "");
                LocalDate dateFrom = LocalDate.parse(fromDate, input);
                LocalDate dateTo = LocalDate.parse(toDate, input);
                task = new Event(eventDescription, dateFrom, dateTo);
                break;
            default:
                throw new FleurCorruptFileException();
            }
            if (command.charAt(4) == 'X') {
                task.markAsDone();
            }
            tasks.add(task);
        }
        br.close();
        return tasks;
    }

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
