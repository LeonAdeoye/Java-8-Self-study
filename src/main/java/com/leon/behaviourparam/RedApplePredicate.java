package com.leon.behaviourparam;

public class RedApplePredicate implements ApplePredicate
{
    public boolean test(Apple apple)
    {
        return "red".equalsIgnoreCase(apple.getColour());
    }
}
