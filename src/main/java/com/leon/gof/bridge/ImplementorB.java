package com.leon.gof;

public class ImplementorB implements Implementor
{
    @Override
    public void implement()
    {
        System.out.println("Implementor B has its own implementation.");
    }
}
