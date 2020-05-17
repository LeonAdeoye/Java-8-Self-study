package com.leon.gof;

public class DecoratorBImplementation implements Component
{
    private Component component;

    public DecoratorBImplementation(Component component)
    {
        this.component = component;
    }

    public void doSomething()
    {
        this.additionalResponsibilityBefore();

        if(this.component != null)
            this.component.doSomething();
    }

    public void additionalResponsibilityBefore()
    {
        System.out.println("Decorator B implementation doing something before.");
    }
}
