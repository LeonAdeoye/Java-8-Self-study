package com.leon.gof.bridge;

public class Client
{
    Abstraction abstraction;

    public Client(Abstraction abstraction)
    {
        this.abstraction = abstraction;
    }

    public void doSomething()
    {
        abstraction.doSomething();
    }
}
