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
            for(int count = 0; count < 20; ++count)
            {
                System.out.println("Taking from the queue: " + queue.take());
                Thread.sleep(1);
            }
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }

    }
}
