package fleur;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {

    private static String dataFile;

    public Storage(String dataFile) {
        this.dataFile = dataFile;
    }

    protected ArrayList<Task> loadData() throws IOException, FleurCorruptFileException {
        ArrayList<Task> tasks = new ArrayList<>();
        int count = 0;
        DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
                    String from = arr[1].split("to: ")[0];
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
            count++;
        }
        br.close();
        return tasks;
    }

    protected void saveData(ArrayList<Task> tasks) throws IOException {
        StringBuilder data = new StringBuilder();
        for (Task task : tasks) {
            data.append(task.toString()).append("\n");
        }
        FileWriter fw = new FileWriter(dataFile, false);
        fw.write(data.toString());
        fw.close();
    }
}
