package com.leon.gof.bridge;

public class ImplementorA implements Implementor
{
    @Override
    public void implement()
    {
        System.out.println("Implementor A has its own implementation.");
    }
}
