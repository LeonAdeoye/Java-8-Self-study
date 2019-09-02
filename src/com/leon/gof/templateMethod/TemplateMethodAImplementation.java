package com.leon.gof.templateMethod;

// Implements the primitive operations to carry out subclass-specific steps of the algorithm.
public class TemplateMethodAImplementation extends TemplateMethodImplementation
{
    // Hook method.
    @Override
    public void primitiveOperation1()
    {
        System.out.println("\nTemplate method A implementation primitive operation ONE doing something.");
    }

    // Hook method.
    @Override
    public void primitiveOperation3()
    {
        super.primitiveOperation3();
        System.out.println("Template method A implementation primitive operation THREE doing something AGAIN.");
    }
}
