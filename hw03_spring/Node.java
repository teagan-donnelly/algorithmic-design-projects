package hw03_spring;

//Teagan Donnelly

public class Node<T>{
    //holds the actual data stored in this node
    private T data;
    //pointer to the next node in the list
    private Node<T> next;

    public Node(T aData)
    {
        //set the data and initialize next to null
        data = aData;
        next = null;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T aData)
    {
        data = aData;
    }

    public Node<T> getNext()
    {
        return next;
    }

    public void setNext(Node<T> aNext)
    {
        next = aNext;
    }
}

