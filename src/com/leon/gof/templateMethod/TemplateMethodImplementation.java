package com.leon.gof.templateMethod;

// Implements the template method defining the skeleton of an algorithm.
// The template method call the primitive operations defined in the abstract class as well as those primitive operations defined in subclasses.
public abstract class TemplateMethodImplementation implements TemplateMethodInterface
{
    @Override
    public void templateMethod()
    {
        primitiveOperation1();
        primitiveOperation2();
        primitiveOperation3();
    }

    @Override
    public void primitiveOperation1()
    {
        System.out.println("\nPrimitive operation 1 doing something.");
    }

    @Override
    public void primitiveOperation2()
    {
        System.out.println("Primitive operation 2 doing something.");
    }

    @Override
    public void primitiveOperation3()
    {
        System.out.println("Primitive operation 3 doing something.");
    }
}
