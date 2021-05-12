package com.leon.blockQ;

public class Consumer implements Runnable
{
    private BlockQueue queue;
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
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }

    }
}
