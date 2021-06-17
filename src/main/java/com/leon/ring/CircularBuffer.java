package com.leon.ring;

public class CircularBuffer<E>
{
    // Initial Capacity of Buffer
    private int capacity = 0;
    // Initial Size of Buffer
    private int size = 0;
    // Head pointer
    private int head = 0;
    // Tail pointer
    private int tail = -1;
    private E[] array;

    // Constructor
    CircularBuffer(int capacity)
    {
        // Initializing the capacity of the array
        this.capacity = capacity;

        // Initializing the array
        array = (E[]) new Object[capacity];
    }

    // Addition of elements
    public boolean add(E element)
    {

        // Calculating the index to add the element
        int index = (tail + 1) % capacity;

        // Size of the array increases as elements are added
        size++;

        // Checking if the array is full
        if (size == capacity)
        {
            return false;
        }

        // Storing the element in the array
        array[index] = element;

        // Incrementing the tail pointer to point
        // to the element added currently
        tail++;

        return true;
    }

    // Deletion of elements
    public E get()
    {

        // Checking if the array is empty
        if (size == 0)
        {
            return null;
        }

        // Calculating the index of the element to be
        // deleted
        int index = head % capacity;

        // Getting the element
        E element = array[index];

        // Incrementing the head pointer to point
        // to the next element
        head++;

        // Decrementing the size of the array as the
        // elements are deleted
        size--;

        // Returning the first element
        return element;
    }

    // Retrieving the first element without deleting it
    public E peek()
    {

        // Checking if the array is empty
        if (size == 0)
        {
            return null;
        }

        // Calculating the index of the
        // element to be deleted
        int index = head % capacity;

        // Getting the element
        E element = array[index];

        // Returning the element
        return element;
    }

    // Checking if the array is empty
    public boolean isEmpty() { return size == 0; }

    // Size of the array
    public int size() { return size; }
}
