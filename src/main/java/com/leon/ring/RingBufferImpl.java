package com.leon.ring;

import java.util.concurrent.TimeUnit;

public class RingBufferImpl<E> implements RingBuffer<E>
{
    private final static int DEFAULT_CAPACITY = 1024;
    private volatile int writePointer = 0, readPointer = -1;
    private int capacity = 0;
    private E[] buffer;

    public RingBufferImpl(int capacity)
    {
        this.capacity =  capacity > 0 ? capacity : DEFAULT_CAPACITY;
        this.buffer = (E[]) new Object[capacity];
    }

    private boolean isFull()
    {
        return size() == capacity;
    }

    private boolean isEmpty()
    {
        return writePointer < readPointer;
    }

    @Override
    public boolean offer(E element)
    {
        if(!isFull())
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean offer(E element, long duration, TimeUnit timeUnit)
    {
        return false;
    }

    @Override
    public E poll()
    {
        return null;
    }

    @Override
    public E poll(long duration, TimeUnit timeUnit)
    {
        return null;
    }

    @Override
    public E take()
    {
        return null;
    }

    @Override
    public void put(E element)
    {

    }

    @Override
    public E peek()
    {
        return null;
    }

    @Override
    public int size()
    {
        return (writePointer - readPointer) + 1;
    }
}
