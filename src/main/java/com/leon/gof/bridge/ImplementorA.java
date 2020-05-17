package com.leon.gof;

public class ImplementorA implements Implementor
{
    @Override
    public void implement()
    {
        System.out.println("Implementor A has its own implementation.");
    }
}
