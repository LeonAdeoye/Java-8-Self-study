package com.leon.ring;

import java.util.concurrent.TimeUnit;

public interface RingBuffer<E>
{
    boolean offer(E element);
    boolean offer(E element, long duration, TimeUnit timeUnit);

    E poll();
    E poll(long duration, TimeUnit timeUnit);


    E take();
    void put(E element);

    E peek();
    int size();
    int capacity();
    boolean isFull();
    boolean isNotFull();
    boolean isEmpty();
    boolean isNotEmpty();
}
