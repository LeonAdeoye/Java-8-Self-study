package com.leon.behaviourparam;

// A functional interface must have EXACTLY one abstract method.
// Java.util.function.Predicate<T> interface defines an abstract method named test  that accepts an object of type T and return a boolean result.
@FunctionalInterface
public interface ApplePredicate
{
    // The function descriptor is the abstract method that the interfaces declares: Apple -> boolean
    boolean test(Apple apple);
}
