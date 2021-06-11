package com.leon.ring;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class RingBufferImpl<E> implements RingBuffer<E>
{
    private final static int DEFAULT_CAPACITY = 1024;
    private volatile int writePointer = -1, readPointer = 0;
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
            buffer[++writePointer % capacity] = element;
            return true;
        }
        return false;
    }

    @Override
    public boolean offer(E element, long duration, TimeUnit timeUnit)
    {
        long timeout = TimeUnit.MILLISECONDS.convert(duration, timeUnit);
        Instant start = Instant.now();

        while(isFull())
        {
            if(Duration.between(start, Instant.now()).toMillis() >= timeout)
            {
                return false;
            }
        }

        buffer[++writePointer % capacity] = element;
        return true;
    }

    @Override
    public E poll()
    {
        if(!isEmpty())
        {
            return buffer[readPointer++ % capacity];
        }
        return null;
    }

    @Override
    public E poll(long duration, TimeUnit timeUnit)
    {
        long timeout = TimeUnit.MILLISECONDS.convert(duration, timeUnit);
        Instant start = Instant.now();

        while(isEmpty())
        {
            if(Duration.between(start, Instant.now()).toMillis() >= timeout)
                return null;
        }

        return buffer[readPointer++ % capacity];
    }

    @Override
    public E take()
    {
        try
        {
            while(isEmpty())
            {
                wait();
            }
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }
        finally
        {
            notifyAll();
            return buffer[readPointer++ % capacity];
        }
    }

    @Override
    public void put(E element)
    {
        try
        {
            while(isFull())
            {
                wait();
            }
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }
        finally
        {
            buffer[++writePointer % capacity] = element;
            // Notify all after item has been added.
            if(size() == 1)
                notifyAll();
        }
    }

    @Override
    public E peek()
    {
        return buffer[readPointer % capacity];
    }

    @Override
    public int size()
    {
        return (writePointer - readPointer) + 1;
    }
}
