package fleur;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;
import java.util.ArrayList;

public class Fleur {
//    private static String filePath = "fleur.txt";
//    private static ArrayList<Task> tasks = new ArrayList<>();
//    private static int count = 0;
//    private static final DateTimeFormatter INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    Scanner scanner = new Scanner(System.in);
//
    private Storage storage;
    private TaskList tasks;
    private static Ui ui;
//
    public Fleur(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (FleurException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        //...
    }

    public static void main(String[] args) {
        new Fleur("fleur.txt").run();
    }

}
//
//    public static void main(String[] args) {
//        Fleur fleur = new Fleur(filePath);
//        fleur.run();
//    }
//
//    public void run() {
//
//        ui.welcomeMessage();
//
//        try {
//            storage.loadData();
//        } catch (FleurCorruptFileException | IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//        while (scanner.hasNextLine()) {
//            String str = scanner.nextLine();
//            try {
//                if (str.equalsIgnoreCase("bye")) {
//                    exit();
//                } else if (str.equalsIgnoreCase("list")) {
//                    listTasks();
//                } else if (str.startsWith("mark")) {
//                    int index = Integer.parseInt(str.substring(5)) - 1;
//                    markDone(index);
//                } else if (str.startsWith("unmark")) {
//                    int index = Integer.parseInt(str.substring(7)) - 1;
//                    markUndone(index);
//                } else if (str.startsWith("todo")) {
//                    addToDo(str);
//                } else if (str.startsWith("deadline")) {
//                    addDeadline(str);
//                } else if (str.startsWith("event")) {
//                    addEvent(str);
//                } else if (str.startsWith("delete")) {
//                    int index = Integer.parseInt(str.substring(7)) - 1;
//                    deleteTask(index);
//                } else {
//                    throw new FleurInvalidCommandException();
//                }
//            } catch (FleurException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    private void listTasks() {
//        if (tasks.isEmpty()) {
//            System.out.println("You 'ave non tasks in your list.");
//        } else {
//            System.out.println("'Ere are all ze tasks in your list:");
//            for (int i = 0; i < count; i++) {
//                System.out.println((i + 1) + "." + tasks.get(i).toString());
//            }
//        }
//    }
//
//    private void markDone(int index) {
//        tasks.get(index).markAsDone();
//        System.out.println("Enchanté! I 'ave marked zis task as done:");
//        System.out.println(tasks.get(index).toString());
//    }
//
//    private void markUndone(int index) {
//        tasks.get(index).markAsUndone();
//        System.out.println("Zut! I 'ave marked zis task as not done:");
//        System.out.println(tasks.get(index).toString());
//    }
//
//    private void addToDo(String str) throws FleurMissingDetailsException {
//        try {
//            String desc = str.substring(5);
//            Task t = new ToDo(desc);
//            tasks.add(t);
//            count++;
//            System.out.println("Bah, oui! I 'ave added zis todo task to your list:");
//            System.out.println(t.toString());
//            System.out.println("Now you 'ave " + count + " task(s) in your list.");
//        } catch (StringIndexOutOfBoundsException e) {
//            throw new FleurMissingDetailsException();
//        }
//    }
//
//    private void addDeadline(String str) throws FleurMissingDetailsException, FleurInvalidDateException {
//        try {
//            String desc = str.substring(9).split("/by")[0];
//            String date = str.substring(9).split("/by")[1].trim();
//            LocalDate by = LocalDate.parse(date, INPUT);
//            Task t = new Deadline(desc, by);
//            tasks.add(t);
//            count++;
//            System.out.println("Bah, oui! I 'ave added zis deadline task to your list:");
//            System.out.println(t.toString());
//            System.out.println("Now you 'ave " + count + " task(s) in your list.");
//        } catch (IndexOutOfBoundsException e) {
//            throw new FleurMissingDetailsException();
//        } catch (DateTimeParseException e) {
//            throw new FleurInvalidDateException();
//        }
//    }
//
//    private void addEvent(String str) throws FleurMissingDetailsException, FleurInvalidDateException {
//        try {
//            String[] arr = str.substring(6).split("/from");
//            String desc = arr[0];
//            String from = arr[1].split("/to")[0].trim();
//            String to = arr[1].split("/to")[1].trim();
//            LocalDate dateFrom = LocalDate.parse(from, INPUT);
//            LocalDate dateTo = LocalDate.parse(to, INPUT);
//            Task t = new Event(desc, dateFrom, dateTo);
//            tasks.add(t);
//            count++;
//            System.out.println("Bah, oui! I 'ave added zis event task to your list:");
//            System.out.println(t.toString());
//            System.out.println("Now you 'ave " + count + " task(s) in your list.");
//        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
//            throw new FleurMissingDetailsException();
//        } catch (DateTimeParseException e) {
//            throw new FleurInvalidDateException();
//        }
//    }
//
//    private void deleteTask(int index) {
//        Task deletedTask = tasks.get(index);
//        count--;
//        System.out.println("D'accord, I 'ave removed zis task from your list:");
//        System.out.println(deletedTask.toString());
//        System.out.println("Now you 'ave " + count + " task(s) in your list.");
//        tasks.remove(index);
//    }
//
//    private void exit() {
//        try {
//            saveData();
//            System.out.println("Au revoir, 'ope to see you again soon!");
//        } catch (IOException e) {
//            System.out.println("Mince! Zere was an error when saving your tasks.");
//        }
//    }
//
//}
