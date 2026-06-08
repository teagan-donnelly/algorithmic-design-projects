package H03_TaskManager;

//Teagan Donnelly

public class GenLL<T>
{
    // varible for first node in the linked list
    private Node<T> head;
    //varible for the iterator used for looping through the list
    private Node<T> iterator;

    public GenLL()
    {
        //start with an empty list
        head = null;
        iterator = null;
    }

    public void add(T aData)
    {
        //create a new node to store the data
        Node<T> newNode = new Node<T>(aData);

        //if the list is empty, new node becomes head
        if(head == null)
        {
            head = newNode;
            return;
        }

        Node<T> temp = head;
        while(temp.getNext() != null)
            temp = temp.getNext();

        //comment: attach the new node at the end
        temp.setNext(newNode);
    }

    public boolean contains(T aData)
    {
        //goes through list and check for equality
        Node<T> temp = head;
        while(temp != null)
        {
            if(temp.getData().equals(aData))
                return true;
            temp = temp.getNext();
        }
        return false;
    }

    public boolean remove(T aData)
    {
        //empty list means nothing to remove
        if(head == null)
            return false;

        // check if the head is the one to remove
        if(head.getData().equals(aData))
        {
            head = head.getNext();
            return true;
        }

        //walk through list looking for the node before the target
        Node<T> temp = head;
        while(temp.getNext() != null)
        {
            if(temp.getNext().getData().equals(aData))
            {
                // over the node to remove it
                temp.setNext(temp.getNext().getNext());
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public void reset()
    {
        //reset iterator to start at the head
        iterator = head;
    }

    public boolean hasNext()
    {
        //iterator is null when list is done
        return iterator != null;
    }

    public T getNext()
    {
        //return current data and move iterator forward
        T data = iterator.getData();
        iterator = iterator.getNext();
        return data;
    }
}

