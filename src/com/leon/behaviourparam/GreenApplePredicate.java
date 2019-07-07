package com.leon.behaviourparam;

public class GreenApplePredicate implements ApplePredicate
{
    public boolean test(Apple apple)
    {
        return "green".equalsIgnoreCase(apple.getColour());
    }
}
