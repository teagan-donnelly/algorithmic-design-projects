package hw04_spring;

// Written by Teagan - Robot Command Simulator
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class RobotSimulator {

    //creates varibles for the characters used 
    public static final char EMPTY = '_';
    public static final char OBSTACLE = 'X';
    public static final char ROBOT = 'O';

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        System.out.println("Welcome to the Robot Simulator");

        boolean quit = false;
        while (!quit) { //loops until user quits
            // Prompt for files
            System.out.println("Enter file for the Board");
            String boardFile = kb.nextLine().trim();

            System.out.println();
            System.out.println("Enter file for the Robot Commands");
            String commandFile = kb.nextLine().trim();

            char[][] board = readBoard(boardFile);
            if (board == null) {
                System.out.println("Error reading board file.");
                break;
            }

            ArrayQueue<String> commands = readCommands(commandFile);
            if (commands == null) {
                System.out.println("Error reading command file.");
                break;
            }

            //Initial board print (with robot at top-left)
            int robotRow = 0;
            int robotCol = 0;

            printBoardWithRobot(board, robotRow, robotCol);

            System.out.println("Simulation begin");

            int commandIndex = 0;
            boolean crashed = false;

            while (!commands.isEmpty() && !crashed) {
                String cmd = commands.dequeue();
                if (cmd == null) break;

                System.out.println("Command " + commandIndex);
                commandIndex++;

                //Compute new position
                int newRow = robotRow;
                int newCol = robotCol;

                if (cmd.equalsIgnoreCase("Move Up")) {
                    newRow--;
                } else if (cmd.equalsIgnoreCase("Move Down")) {
                    newRow++;
                } else if (cmd.equalsIgnoreCase("Move Left")) {
                    newCol--;
                } else if (cmd.equalsIgnoreCase("Move Right")) {
                    newCol++;
                } else {
                    //Invalid command: ignore and just reprint board
                    printBoardWithRobot(board, robotRow, robotCol);
                    continue;
                }

                //Check bounds
                if (newRow < 0 || newRow >= board.length ||
                    newCol < 0 || newCol >= board[0].length) {
                    System.out.println("CRASH!");
                    crashed = true;
                    break;
                }

                //Check obstacle
                if (board[newRow][newCol] == OBSTACLE) {
                    System.out.println("CRASH!");
                    crashed = true;
                    break;
                }

                //Update robot position
                robotRow = newRow;
                robotCol = newCol;

                //Print board after command
                printBoardWithRobot(board, robotRow, robotCol);
            }

            System.out.println("Simulation end");
            System.out.println("Quit? Enter \"true\" to quit or hit enter to run another simulation");
            String ans = kb.nextLine().trim();
            if (ans.equalsIgnoreCase("true")) {
                quit = true;
            }
        }

        kb.close();
    }

    //Reads a 10x10 board of '_' and 'X'
    private static char[][] readBoard(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            // We don't know size from spec exactly, but examples are 10x10.
            // To be safe, read all lines into a dynamic buffer first.
            String[] lines = new String[100];
            int count = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.trim().isEmpty()) continue;
                lines[count++] = line;
            }
            fileScanner.close();

            if (count == 0) return null;

            int rows = count;
            int cols = lines[0].length();
            char[][] board = new char[rows][cols];

            for (int r = 0; r < rows; r++) {
                String line = lines[r];
                for (int c = 0; c < cols; c++) {
                    char ch = line.charAt(c);
                    if (ch != EMPTY && ch != OBSTACLE) {
                        // Treat anything else as empty or obstacle? Spec says only '_' or 'X'
                        // We'll just store it as-is.
                    }
                    board[r][c] = ch;
                }
            }
            return board;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    // Reads commands into a queue, ignoring invalid lines later in simulation
    private static ArrayQueue<String> readCommands(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            // Pre-allocate some capacity; can be large enough for homework files
            ArrayQueue<String> q = new ArrayQueue<>(1000);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;
                // We enqueue all; invalid ones are ignored during simulation
                q.enqueue(line);
            }
            fileScanner.close();
            return q;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    // Prints the board with the robot 'O' at (r,c), matching example style as closely as possible
    private static void printBoardWithRobot(char[][] board, int robotRow, int robotCol) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (r == robotRow && c == robotCol) {
                    System.out.print(ROBOT);
                } else {
                    System.out.print(board[r][c]);
                }
            }
            System.out.println();
        }
    }
}

