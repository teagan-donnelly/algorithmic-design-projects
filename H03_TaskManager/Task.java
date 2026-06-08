package H03_TaskManager;

//teagan donnelly 

public class Task
{
    //varible for the description of the task
    private String action;
    //varible for the priority from 0 to 4
    private int priority;

    public Task()
    {
        //default values required by assignment
        action = "none";
        priority = 4;
    }

    public Task(String a, int p)
    {
        //store user-provided values
        action = a;
        priority = p;
    }

    public String getAction()
    {
        return action;
    }

    public int getPriority()
    {
        return priority;
    }

    public boolean equals(Object o)
    {
        //ensures duplicates are detected correctly
        if(o == null || !(o instanceof Task))
            return false;

        Task other = (Task)o;

        return this.priority == other.priority && this.action.equals(other.action);
    }

    public String toString()
    {
        return "[Task] Priority: " + priority + " Task: " + action;
    }
}

