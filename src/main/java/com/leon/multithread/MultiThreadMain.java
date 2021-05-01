package com.leon.multithread;

public class MultiThreadMain
{
    public static void main()
    {
        new SimpleExecutor().execute(() -> System.out.println("simple executor!"));
    }
}
