package com.leon.blockQ;

public class Producer implements Runnable
{
    private BlockQueue queue;
    public Producer(BlockQueue<String> queue)
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
                System.out.println("Putting into the queue: " + count);
                queue.put(String.valueOf(count));
                Thread.sleep(100);
            }
        }
        catch(InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
}
