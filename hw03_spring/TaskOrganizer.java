package hw03_spring;
 //teagan Donnelly
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class TaskOrganizer{
    private GenLL<Task>[] lists;

    public TaskOrganizer()
    {
        //create the array and each linked list inside it
        lists = new GenLL[5];
        for(int i = 0; i < lists.length; i++)
            lists[i] = new GenLL<Task>();
    }

    public void addTask(String action, int priority)
    {
        // invalid priorities
        if(priority < 0 || priority > 4)
            return;

        Task t = new Task(action, priority);

        //prevent duplicates as required
        if(lists[priority].contains(t))
        {
            System.out.println("Duplicate task. Not added.");
            return;
        }

        //adds task to correct list based on priority
        lists[priority].add(t);
    }

    public void removeTask(String action, int priority)
    {
        // ignore invalid priorities
        if(priority < 0 || priority > 4)
            return;

        Task t = new Task(action, priority);

        //remove only from the correct priority list
        lists[priority].remove(t);
    }

    public void printTasks()
    {
        //print tasks from highest priority (0) to lowest (4)
        for(int p = 0; p <= 4; p++)
        {
            lists[p].reset();
            while(lists[p].hasNext())
            {
                System.out.println(lists[p].getNext().toString());
            }
        }
    }

    public void loadFile(String filename)
    {
        try
        {
            //clear all existing tasks before loading new file
            lists = new GenLL[5];
            for(int i = 0; i < lists.length; i++)
                lists[i] = new GenLL<Task>();

            Scanner fileScan = new Scanner(new File(filename));

            // file line by line
            while(fileScan.hasNextLine())
            {
                String line = fileScan.nextLine();

                //ignore lines that don't contain a tab
                if(!line.contains("\t"))
                    continue;

                String[] parts = line.split("\t");

                if(parts.length != 2)
                    continue;

                try
                {
                    int p = Integer.parseInt(parts[0].trim());
                    String action = parts[1].trim();

                    //only add valid priorities
                    if(p >= 0 && p <= 4)
                        addTask(action, p);
                }
                catch(Exception e)
                {
                }
            }

            fileScan.close();
        }
        catch(Exception e)
        {
            System.out.println("File not found.");
        }
    }

    public void saveFile(String filename)
    {
        try
        {
            PrintWriter pw = new PrintWriter(filename);

            //write tasks in priority order
            for(int p = 0; p <= 4; p++)
            {
                lists[p].reset();
                while(lists[p].hasNext())
                {
                    Task t = lists[p].getNext();
                    pw.println(t.getPriority() + "\t" + t.getAction());
                }
            }

            pw.close();
        }
        catch(Exception e)
        {
            System.out.println("Could not write file.");
        }
    }
}
