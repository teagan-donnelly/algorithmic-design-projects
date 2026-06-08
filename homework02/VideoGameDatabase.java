package homework02;
// written by Teagan Donnelly

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class VideoGameDatabase {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        GameList<VideoGame> gameList = new GameList<>();
        GameList<VideoGame> results = new GameList<>();

        System.out.println("Welcome to the Video Game Database!");

        int choice = -1;

        while (choice != 0) {

            System.out.println("\nEnter 1 to load the video game database");
            System.out.println("Enter 2 to search the database");
            System.out.println("Enter 3 to print current results to the console");
            System.out.println("Enter 4 to print current results to file");
            System.out.println("Enter 0 to quit");

            choice = Integer.parseInt(keyboard.nextLine());

            //load file
            if (choice == 1) {
                System.out.println("Enter the file name");
                String filename = keyboard.nextLine();

                gameList.clear();

                try {
                    Scanner fileScanner = new Scanner(new File(filename));

                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();

                        if (!line.contains("\t")) continue;

                        String[] parts = line.split("\t");

                        if (parts.length != 2) continue;

                        String title = parts[0].trim();
                        String console = parts[1].trim();

                        VideoGame g = new VideoGame(title, console);
                        gameList.addItem(g);
                    }
                    fileScanner.close();
                } catch (Exception e) {
                    System.out.println("Error loading file.");
                }
            }

            //search
            else if (choice == 2) {
                System.out.println("Enter the name of the game or '*' for all");
                String titleQuery = keyboard.nextLine().trim();

                System.out.println("Enter the name of the console or '*' for all");
                String consoleQuery = keyboard.nextLine().trim();

                results.clear();

                gameList.resetCurrent();

                VideoGame g = gameList.getCurrent();

                while (g != null) {
                    if (g.matches(titleQuery, consoleQuery)) {
                        results.addItem(g);
                    }
                    gameList.gotoNext();
                    g = gameList.getCurrent();
                }

                results.showList();
            }

            //prints results to console
            else if (choice == 3) {
                results.showList();
            }

            //prints results to file
            else if (choice == 4) {
                System.out.println("Enter the file name");
                String outName = keyboard.nextLine();

                System.out.println("Would you like to append? True or false?");
                boolean append = Boolean.parseBoolean(keyboard.nextLine());

                try {
                    PrintWriter pw = new PrintWriter(new java.io.FileWriter(outName, append));

                    results.resetCurrent();
                    VideoGame g2 = results.getCurrent();

                    while (g2 != null) {
                        pw.println(g2.toString());
                        results.gotoNext();
                        g2 = results.getCurrent();
                    }

                    pw.close();

                } catch (Exception e) {
                    System.out.println("Error writing to file.");
                }
            }
        }
        System.out.println("Goodbye!");
        keyboard.close();
    }

}
