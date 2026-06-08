package hw07_spring;

//Teagan Donnelly

public class MinHeap<T extends Comparable<T>> {
    private T[] heap;
    private int size;

    //Default constructor
    @SuppressWarnings("unchecked")
    public MinHeap() {
        heap = (T[]) new Comparable[64]; //initial capacity
        size = 0;
    }

    //Returns true if heap is empty
    public boolean isEmpty() {
        return size == 0;
    }

    //Adds an element to the heap
    public void add(T value) {
        if (size == heap.length) {
            grow();
        }
        heap[size] = value;
        bubbleUp(size);
        size++;
    }

    //Removes and returns the minimum element
    public T remove() {
        if (size == 0) {
            return null;
        }
        T min = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        bubbleDown(0);
        return min;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        T[] newHeap = (T[]) new Comparable[heap.length * 2];
        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    private void bubbleUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap[index].compareTo(heap[parent]) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void bubbleDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;

            if (left < size && heap[left].compareTo(heap[smallest]) < 0) {
                smallest = left;
            }
            if (right < size && heap[right].compareTo(heap[smallest]) < 0) {
                smallest = right;
            }
            if (smallest == index) {
                break;
            }
            swap(index, smallest);
            index = smallest;
        }
    }

    //Swaps two elements in the heap array
    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}

