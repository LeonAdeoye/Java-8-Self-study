package com.leon.gof.singleton;

public class Singleton
{
    private final static Singleton instance = new Singleton();

    private Singleton()
    {

    }

    public static Singleton getInstance()
    {
        return instance;
    }

    public static void doSomething()
    {
        System.out.println("\nSingleton doing something.");
    }
}
