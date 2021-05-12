package com.leon.blockQ;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class BlockQueueImpl<T> implements BlockQueue<T>
{
    private LinkedList<T> queue = new LinkedList<>();
    private int maxSize = 10;

    public BlockQueueImpl(int maxSize)
    {
        this.maxSize = maxSize;
    }

    // The Java BlockingQueue add() method will add the element passed as parameter to this method if the BlockingQueue
    // has space for it internally. If the BlockingQueue does not have space internally for this new element,
    // the add() method throws an IllegalStateException.
    @Override
    public synchronized void add(T item) throws IllegalStateException
    {
        if(queue.size() == maxSize)
            throw new IllegalStateException();

        queue.add(item);
    }

    // The BlockingQueue offer() method will add the element passed as parameter to this method if the BlockingQueue has
    // space for it internally. If the BlockingQueue does not have space internally for this new element, the offer() method return false.
    @Override
    public synchronized boolean offer(T item)
    {
        if(queue.size() == maxSize)
            return false;

        queue.add(item);
        return true;
    }

    // The BlockingQueue offer() method exists in a version which takes a time out as parameter. This version of the offer() method
    // will add the element passed as parameter if the BlockingQueue has space for it internally, or space becomes available.
    // If the BlockingQueue does not have or get space internally for this new element within the time out, this version of the offer() method returns false.
    @Override
    public synchronized boolean offer(T item, long duration, TimeUnit timeUnit)
    {
        long timeout = TimeUnit.MILLISECONDS.convert(duration, timeUnit);
        Instant start = Instant.now();

        while(queue.size() == maxSize)
        {
            if(Duration.between(start, Instant.now()).toMillis() >= timeout)
                return false;
        }

        queue.add(item);
        return true;
    }

    // The BlockingQueue put() method inserts the element into the BlockingQueue if it has space for it internally.
    // If the BlockingQueue does not have space for the new element, the put() method will block the thread calling
    // the put() method until the BlockingQueue as space internally for the new element.
    @Override
    public synchronized void put(T item)
    {
        try
        {
            while(queue.size() == maxSize)
                wait();
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }
        finally
        {
            queue.add(item);
            // Notify all after item has been added.
            if(queue.size() == 1)
                notifyAll();
        }
    }

    // The Java BlockingQueue take() method will remove the first element in the BlockingQueue. If the BlockingQueue does
    // not contain any elements, the take() method will block the thread calling take() until an element is inserted into the BlockingQueue.
    @Override
    public synchronized T take()
    {
        try
        {
            while(queue.size() == 0)
                wait();
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }
        finally
        {
            notifyAll();
            return queue.remove(0);
        }
    }

    // The BlockingQueue poll() method will remove the first element in the BlockingQueue.
    // If the BlockingQueue does not contain any elements, the poll() method will return null.
    @Override
    public synchronized T poll()
    {
        if(queue.size() == 0)
            return null;
        else
            return queue.remove(0);
    }

    // The BlockingQueue poll(long timeMillis, TimeUnit timeUnit) method will remove the first element in the BlockingQueue.
    // If the BlockingQueue does not contain any elements, this version of the poll() method will wait for an element to
    // become available for the given amount of time passed to it as parameter. If no element becomes available within
    // the given time out period, this method returns null.
    @Override
    public synchronized T poll(long duration, TimeUnit timeUnit)
    {
        long timeout = TimeUnit.MILLISECONDS.convert(duration, timeUnit);
        Instant start = Instant.now();

        while(queue.size() == 0)
        {
            if(Duration.between(start, Instant.now()).toMillis() >= timeout)
                return null;
        }

        return queue.remove(0);
    }

    // The BlockingQueue peek() method will return the first element of the BlockingQueue without removing it.
    // If the BlockingQueue does not contain any elements, the peek() method will return null.
    @Override
    public synchronized T peek()
    {
        if(queue.size() == 0)
            return null;
        else
            return queue.peek();
    }


    //The BlockingQueue element() method will return the first element of the BlockingQueue without removing it.
    // If the BlockingQueue does not contain any elements, the element() method will throw a NoSuchElementException
    @Override
    public synchronized T element() throws NoSuchElementException
    {
        if(queue.size() == 0)
            throw new NoSuchElementException();
        else
            return queue.element();
    }
}
