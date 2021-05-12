package com.leon.blockQ;

public class BlockingQueueExample
{
    public static void start()
    {
        BlockQueue<String> queue = new BlockQueueImpl<>(5);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(consumer).start();
        new Thread(producer).start();
    }
}
