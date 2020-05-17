package com.leon.gof;

public class ContextImplementation
{
    private Strategy strategy;

    public ContextImplementation(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public void doSomething()
    {
        this.strategy.doSomething();
    }

    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }
}
