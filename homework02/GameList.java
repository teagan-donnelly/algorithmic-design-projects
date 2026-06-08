package homework02;
//written by Teagan Donnelly

public class GameList<T> {
    //Internal class
    public class ListNode {
        private T data;
        private ListNode link;

        //Default constructor
        public ListNode() {
            data = null;
            link = null;
        }

        //Parameterized constructor
        public ListNode(T aData, ListNode aLink) {
            data = aData;
            link = aLink;
        }

    }

    //Instance Variables
    private ListNode head;
    private ListNode current;
    private ListNode previous;

    //Constructor
    public GameList() {
        head = new ListNode();  //empty node
        current = head;
        previous = head;
    }

    //moves the current node forward in the list by one node if it isnt null
    public void gotoNext() {
        if (current != null) {
            previous = current;
            current = current.link; // will become null at end of list
        }
    }


    //returns the data at the current node as long as the current isn’t null
    public T getCurrent(){
        if(current != null){
            return current.data;
        } else {
            return null;
        }
    }

    //sets current's data = to type is current and type isnt null
    public void setCurrent(T type){
        if(current != null && type!= null){
            current.data = type;
        }
    }

    public void resetCurrent(){
        current = head;
        previous = head;
    }

    //to help debug
    public int size() {
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            if (temp.data != null) count++;
            temp = temp.link;
        }
        return count;
    }


    // Add item to the end of the list
    public void addItem(T aItem) {
        if (aItem == null) return;

        // If list is empty (head has no data)
        if (head.data == null) {
            head.data = aItem;
            return;
        }

        // Otherwise walk to end
        ListNode temp = head;
        while (temp.link != null) {
            temp = temp.link;
        }

        temp.link = new ListNode(aItem, null);

    }

    
    // Add item after current
    public void addItemAfterCurrent(T aItem) {
        if (aItem == null || current == null) return;

        ListNode newNode = new ListNode(aItem, current.link);
        current.link = newNode;
    }

    //Remove the current node
    public void removeCurrent() {
        if (current == null) return;

        //Case 1: removing head
        if (current == head) {
            head = head.link;
            current = head;
            previous = head;
            return;
        }

        //Case 2: removing middle/end
        previous.link = current.link;
        current = current.link;
    }

    // clears the list
    public void clear() {
        head = new ListNode();
        current = head;
        previous = head;
    }


    
    // Print list contents
    public void showList() {
        ListNode temp = head;
        while (temp != null) {
            if (temp.data != null) {
                System.out.println(temp.data.toString());
            }
            temp = temp.link;
        }
    }

    // Check if list contains a given item
    public boolean contains(T aItem) {
        if (aItem == null) return false;

        ListNode temp = head;
        while (temp != null) {
            if (temp.data != null && temp.data.equals(aItem)) {
                return true;
            }
            temp = temp.link;
        }
        return false;
    }
}
