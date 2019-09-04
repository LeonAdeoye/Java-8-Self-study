package com.leon.gof.bridge;

public class AbtractionImplementation implements Abstraction
{
    private Implementor implementor;

    public AbtractionImplementation(Implementor implementor)
    {
        this.implementor = implementor;
    }

    public void doSomething()
    {
        implementor.implement();
    }
}
