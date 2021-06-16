package com.leon.ring;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RingBufferMain
{
    // TODO need to figure out why 2000 capacity does not work.
    private RingBuffer<String> ringBuffer = new RingBufferImpl<>(2000);

    public int produce()
    {
        int count;
        for(count = 0; count < 10000000;)
        {
            if(ringBuffer.offer("element " + count))
                count++;
        }
        return count;
    }

    public int consume()
    {
        int count;
        for(count = 0; count < 10000000;)
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
        for(count = 0; count < 10000000;)
        {
            ringBuffer.put("element " + count);
            count++;
        }
        return count;
    }

    public int consumeWithBlock()
    {
        int count;
        for(count = 0; count < 10000000;)
        {
            ringBuffer.take();
            count++;
        }
        return count;
    }


    public void main()
    {
        CompletableFuture<Integer> producerFuture = CompletableFuture.supplyAsync(() -> produce());
        CompletableFuture<Integer> consumerFuture = CompletableFuture.supplyAsync(() -> consume());
        Instant currentInstant = Instant.now();
        List<Integer> combined = Stream.of(producerFuture, consumerFuture)
                .map(CompletableFuture::join)
                .collect(toList());

        System.out.print("\nProduced " + combined.get(0) + " elements and consumed " + combined.get(1) + " elements in " + Duration.between(currentInstant, Instant.now()).toMillis() + "ms.\n");


        CompletableFuture<Integer> producerFutureWithBlock = CompletableFuture.supplyAsync(() -> produceWithBlock());
        CompletableFuture<Integer> consumerFutureWithBlock = CompletableFuture.supplyAsync(() -> consumeWithBlock());
        currentInstant = Instant.now();
        List<Integer> combinedWithBlock = Stream.of(producerFutureWithBlock, consumerFutureWithBlock)
                .map(CompletableFuture::join)
                .collect(toList());

        System.out.print("\nProduced " + combinedWithBlock.get(0) + " elements and consumed " + combinedWithBlock.get(1) + " elements with blocking in " + Duration.between(currentInstant, Instant.now()).toMillis() + "ms.\n");
    }
}
