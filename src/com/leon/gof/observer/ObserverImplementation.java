package com.leon.gof.observer;

public class ObserverImplementation implements ObserverInterface
{
    private String name;

    public ObserverImplementation(String name)
    {
        this.name = name;
    }

    public void update(String newState)
    {
        System.out.println("\nThe observer named " + name + " has something to say: " + newState);
    }

    @Override
    public String toString()
    {
        return "ObserverImplementation{" + "name='" + name + '\'' + '}';
    }
}
