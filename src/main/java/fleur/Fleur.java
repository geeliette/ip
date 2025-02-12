package fleur;

import java.io.IOException;

public class Fleur {

    private Storage storage;
    private TaskList tasks;
    private static Ui ui;

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

    public static void main(String[] args) throws IOException {
        new Fleur("fleur.txt").run();
    }

}