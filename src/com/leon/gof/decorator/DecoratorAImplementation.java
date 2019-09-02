package com.leon.gof.decorator;

// Maintains a reference to a component object so that it can forward requests to it.
// It may optionally perform additional operations before/after it forwards the request.
public class DecoratorAImplementation implements Component
{
    private Component component;

    public DecoratorAImplementation(Component component)
    {
        this.component = component;
    }

    public void doSomething()
    {
        if(this.component != null)
            this.component.doSomething();

        this.additionalResponsibilityAfter();
    }

    public void additionalResponsibilityAfter()
    {
        System.out.println("Decorator A implementation doing something after.");
    }
}
