package com.leon.blockQ;

import java.util.concurrent.TimeUnit;

public class Producer implements Runnable
{
    private BlockQueue<String> queue;
    public Producer(BlockQueue<String> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            // In scenario ONE the producer adds faster than the consumer can take out.
            // It cannot add more than limit and can only add only after one item is taken out.
            for(int count = 0; count < 20; ++count)
            {
                System.out.println("Putting into the queue: " + count);
                queue.put(String.valueOf(count));
                Thread.sleep(1);
            }

            for(int count = 100; count < 120; ++count)
            {
                System.out.println("Offer into the queue: " + count);
                while(!queue.offer(String.valueOf(count), 5, TimeUnit.MILLISECONDS));
                Thread.sleep(10);
            }
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
}
