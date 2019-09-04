package com.leon.gof.facade;

// Provides a unified interface to set of interfaces.
// Defines a higher-level interface that makes the subsystem easier to use.
// Client of the facade don't have to access the sub system objects directly.
public class Facade
{
    private SubSystemA a;
    private SubSystemB b;

    public Facade()
    {
        a = new SubSystemA();
        b = new SubSystemB();
    }

    public void doSomething()
    {
        a.methodOne();
        b.methodTwo();
    }
}
