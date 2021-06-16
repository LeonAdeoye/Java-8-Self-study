package com.leon.ring;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RingBufferMain
{
    private RingBuffer<String> ringBuffer = new RingBufferImpl<>(20);

    public int produce()
    {
        int count;
        for(count = 0; count < 1000;)
        {
            if(ringBuffer.offer("element " + count))
                count++;
        }
        return count;
    }

    public int consume()
    {
        int count;
        for(count = 0; count < 1000;)
        {
            String element = ringBuffer.poll();
            if(element != null)
                count++;
        }
        return count;
    }

    public int produceWithBlock()
    {
        int count;
        for(count = 0; count < 1000;)
        {
            ringBuffer.put("element " + count);
            count++;
        }
        return count;
    }

    public int consumeWithBlock()
    {
        int count;
        for(count = 0; count < 1000;)
        {
            String element = ringBuffer.take();
            count++;
        }
        return count;
    }


    public void main()
    {
        CompletableFuture<Integer> producerFuture = CompletableFuture.supplyAsync(() -> produce());
        CompletableFuture<Integer> consumerFuture = CompletableFuture.supplyAsync(() -> consume());

        List<Integer> combined = Stream.of(producerFuture, consumerFuture)
                .map(CompletableFuture::join)
                .collect(toList());

        System.out.print("\nProduced " + combined.get(0) + " elements and consumed " + combined.get(1) + " elements.\n");

        CompletableFuture<Integer> producerFutureWithBlock = CompletableFuture.supplyAsync(() -> produceWithBlock());
        CompletableFuture<Integer> consumerFutureWithBlock = CompletableFuture.supplyAsync(() -> consumeWithBlock());

        List<Integer> combinedWithBlock = Stream.of(producerFutureWithBlock, consumerFutureWithBlock)
                .map(CompletableFuture::join)
                .collect(toList());

        System.out.print("\nProduced " + combinedWithBlock.get(0) + " elements and consumed " + combinedWithBlock.get(1) + " elements with blocking.\n");
    }
}
