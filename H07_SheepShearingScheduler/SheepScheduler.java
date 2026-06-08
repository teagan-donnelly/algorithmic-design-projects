package hw07_spring;

//Teagan Donnelly

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SheepScheduler {

    //allows user to run multiple times
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        boolean runAgain = true;
        while (runAgain) {
            System.out.print("Enter sheep scheduling filename: ");
            String filename = keyboard.nextLine().trim();

            Sheep[] sheepArray = readSheepFile(filename);
            if (sheepArray == null) {
                System.out.println("Could not read file. Please try again.");
            } else {
                simulateSchedule(sheepArray);
            }

            System.out.print("Would you like to run again? (y/n): ");
            String answer = keyboard.nextLine().trim().toLowerCase();
            runAgain = answer.equals("y") || answer.equals("yes");
        }

        keyboard.close();
    }

    //Reads the sheep scheduling file and returns an array of Sheep
    private static Sheep[] readSheepFile(String filename) {
        File file = new File(filename);

        //count lines
        int count = 0;
        try {
            Scanner fileScan = new Scanner(file);
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine().trim();
                if (!line.isEmpty()) {
                    count++;
                }
            }
            fileScan.close();
        } catch (FileNotFoundException e) {
            return null;
        }

        //read data into array
        Sheep[] sheepArray = new Sheep[count];
        int index = 0;
        try {
            Scanner fileScan = new Scanner(file);
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\t");
                if (parts.length < 3) {
                    continue;
                }
                String name = parts[0].trim();
                int shearTime = Integer.parseInt(parts[1].trim());
                int arrivalTime = Integer.parseInt(parts[2].trim());

                sheepArray[index] = new Sheep(name, shearTime, arrivalTime);
                index++;
            }
            fileScan.close();
        } catch (FileNotFoundException e) {
            return null;
        }

        return sheepArray;
    }

    //Simulates the sheep shearing schedule using priority scheduling and prints it
    private static void simulateSchedule(Sheep[] sheepArray) {
        if (sheepArray == null || sheepArray.length == 0) {
            System.out.println("No sheep to schedule.");
            return;
        }

        //Sort sheep by arrival time 
        sortByArrivalTime(sheepArray);

        MinHeap<Sheep> waitHeap = new MinHeap<Sheep>();

        int time = 0;
        int index = 0;
        int n = sheepArray.length;
        int processed = 0;

        //Start time at first sheep's arrival
        time = sheepArray[0].getArrivalTime();

        System.out.println("Schedule from the Provided File:");
        System.out.println();

        //Main simulation loop
        while (processed < n) {
            //Add all sheep that have arrived by current time to the wait heap
            while (index < n && sheepArray[index].getArrivalTime() <= time) {
                waitHeap.add(sheepArray[index]);
                index++;
            }

            //If no sheep are waiting but some are still to arrive, jump time forward
            if (waitHeap.isEmpty() && index < n) {
                time = sheepArray[index].getArrivalTime();
                continue;
            }

            //Remove next sheep to shear 
            Sheep current = waitHeap.remove();
            if (current == null) {
                break;
            }

            System.out.println("Name: " + current.getName() +
                    ", Shear Time: " + current.getShearingTime() +
                    ", Arrival Time: " + current.getArrivalTime());
            System.out.println();

            time += current.getShearingTime();
            processed++;
        }
    }

    //Sorts the array of sheep by arrival time
    private static void sortByArrivalTime(Sheep[] sheepArray) {
        int n = sheepArray.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (sheepArray[j].getArrivalTime() < sheepArray[minIndex].getArrivalTime()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Sheep temp = sheepArray[i];
                sheepArray[i] = sheepArray[minIndex];
                sheepArray[minIndex] = temp;
            }
        }
    }
}
