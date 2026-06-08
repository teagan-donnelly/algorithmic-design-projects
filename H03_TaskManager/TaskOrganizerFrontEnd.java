package H03_TaskManager;

//Teagan Donnelly

import java.util.Scanner;

public class TaskOrganizerFrontEnd
{
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);
        TaskOrganizer org = new TaskOrganizer();

        System.out.println("Welcome to the Task Organizer!");
        System.out.println();
        printMenu();

        int choice = kb.nextInt();
        kb.nextLine();

        //loop until the user chooses 9
        while(choice != 9)
        {
            if(choice == 1)
            {
                //adding a task
                System.out.println("Enter the task's priority");
                int p = kb.nextInt();
                kb.nextLine();
                System.out.println();
                System.out.println("Enter the task's action");
                String action = kb.nextLine();
                System.out.println();
                org.addTask(action, p);
            }
            else if(choice == 2)
            {
                //removing a task
                System.out.println("Enter the task's priority");
                int p = kb.nextInt();
                kb.nextLine();
                System.out.println();
                System.out.println("Enter the task's action");
                String action = kb.nextLine();
                System.out.println();
                org.removeTask(action, p);
            }
            else if(choice == 3)
            {
                //prints all tasks
                org.printTasks();
                System.out.println();
            }
            else if(choice == 4)
            {
                //load tasks from file
                System.out.println("Enter the file name");
                String file = kb.nextLine();
                System.out.println();
                org.loadFile(file);
            }
            else if(choice == 5)
            {
                //save tasks to file
                System.out.println("Enter the file name");
                String file = kb.nextLine();
                System.out.println();
                org.saveFile(file);
            }

            //show menu again after each action
            printMenu();
            choice = kb.nextInt();
            kb.nextLine();
        }

        System.out.println("Goodbye!");
    }

    public static void printMenu()
    {
        //options menu
        System.out.println("Enter 1. To Add a Task");
        System.out.println();
        System.out.println("Enter 2. To Remove a Task");
        System.out.println();
        System.out.println("Enter 3. To Print Tasks To Console");
        System.out.println();
        System.out.println("Enter 4. To Read from a Task File");
        System.out.println();
        System.out.println("Enter 5. To Write to a Task File");
        System.out.println();
        System.out.println("Enter 9. To Quit");
        System.out.println();
    }
}

