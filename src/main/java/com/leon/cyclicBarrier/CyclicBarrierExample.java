package com.leon.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample
{
    public static void run()
    {
        // The CyclicBarrier class is a synchronization mechanism that can synchronize threads progressing through some algorithm.
        // In other words, it is a barrier that all threads must wait at, until all threads reach it, before any of the threads can continue.
        // The threads wait for each other by calling the await() method on the CyclicBarrier.
        // Once N threads are waiting at the CyclicBarrier, all threads are released and can continue running.
        // You can also specify a timeout for the waiting thread. When the timeout has passed the thread is also released,
        // even if not all N threads are waiting at the CyclicBarrier.
        Runnable barrierAction1 = () -> System.out.println("barrier 1 action");
        Runnable barrierAction2 = () -> System.out.println("barrier 2 action");

        CyclicBarrier barrier1 = new CyclicBarrier(2, barrierAction1);
        CyclicBarrier barrier2 = new CyclicBarrier(2, barrierAction2);

        CyclicBarrierRunnable barrierRunnable1 = new CyclicBarrierRunnable(barrier1, barrier2);
        CyclicBarrierRunnable barrierRunnable2 = new CyclicBarrierRunnable(barrier1, barrier2);

        new Thread(barrierRunnable1).start();
        new Thread(barrierRunnable2).start()
    }
}
