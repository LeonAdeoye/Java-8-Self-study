package com.leon.blockQ;

import java.util.concurrent.TimeUnit;

public interface BlockQueue<T>
{
    void add(T item);
    boolean offer(T item);
    boolean offer(T item, long millis, TimeUnit timeUnit);
    void put(T item);
    T take();
    T poll();
    T poll(long timeMillis, TimeUnit timeUnit);
    T peek();
    T element();
}
