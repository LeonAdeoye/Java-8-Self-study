package com.leon.blockQ;

import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable
{
    private BlockQueue<String> queue;
    public Consumer(BlockQueue<String> queue)
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
                System.out.println("Taking from the queue: " + queue.take());
                Thread.sleep(100);
            }

            for(int count = 100; count < 120; ++count)
            {
                System.out.println("Poll the queue: " );
                String result = queue.poll(5, TimeUnit.MILLISECONDS);
                if(result != null)
                    System.out.println("Polled the queue for: " + result);

                Thread.sleep(10);
            }
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }

    }
}
