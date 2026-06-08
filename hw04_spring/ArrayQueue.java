package hw04_spring;

// Written by Teagan - Array-based generic queue
public class ArrayQueue<T> {
    private T[] data;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        data = (T[]) new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    //adds an item to the back of the queue
    public void enqueue(T item) {
        if (isFull()) return; //ignore if full
        data[rear] = item;
        rear = (rear + 1) % data.length;
        size++;
    }

    //removes item at the front (and returns it)
    public T dequeue() {
        if (isEmpty()) return null;
        T item = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return item;
    }

    //returns item at the front
    public T peek() {
        if (isEmpty()) return null;
        return data[front];
    }
}

