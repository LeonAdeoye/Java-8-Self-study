package com.leon.gof.decorator;


// Defines an object to which additional responsibilities can be attached.
public class ComponentImplementation implements Component
{
    public void doSomething()
    {
        System.out.println("Component implementation doing something.");
    }
}
