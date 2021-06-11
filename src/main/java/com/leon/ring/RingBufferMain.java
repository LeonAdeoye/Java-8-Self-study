package com.leon.ring;

public class RingBufferMain
{
    public static void main()
    {
        RingBuffer<String> ringBuffer = new RingBufferImpl<>(20);

        Runnable producer = () ->
        {
            for(int i = 0; i < 1000; ++i)
            {
                ringBuffer.offer("element: "  + i);
            }
        };

        Runnable consumer = () ->
        {
            for(int i = 0; i < 1000; ++i)
            {
                System.out.println(ringBuffer.poll());
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
