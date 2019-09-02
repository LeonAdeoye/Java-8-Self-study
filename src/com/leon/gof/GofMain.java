package com.leon.gof;

import com.leon.gof.decorator.*;
import com.leon.gof.observer.*;
import com.leon.gof.strategy.*;
import com.leon.gof.templateMethod.*;


public class GofMain
{
    public void main()
    {
        // Define a family of algorithms, encapsulate each one, and make them interchangeable.
        // Strategy lets the algorithm vary independently of the clients that use it.
        ContextImplementation context = new ContextImplementation(new StrategyAImplementation());
        context.doSomething();

        context.setStrategy(new StrategyBImplementation());
        context.doSomething();

        // Define a one-to-many dependency between objects so that when the object change states,
        // all it dependents are notified and updated automatically.

        SubjectImplementation subject = new SubjectImplementation();

        ObserverInterface saori = new ObserverImplementation("Saori");
        subject.attach(new ObserverImplementation("Horatio"));
        subject.attach(new ObserverImplementation("Harper"));
        subject.attach(saori);

        subject.notifyObservers("Leon departed for work");

        subject.detach(saori);
        subject.notifyObservers("Leon arrived back from work");

        // Attach additional responsibilities to an object dynamically.
        // Decorators provide a flexible alternative to subclassing in order to extend features/functions.
        // Also know as a wrapper.
        Component component = new ComponentImplementation();
        component.doSomething();

        Component decoratorA = new DecoratorAImplementation(component);
        decoratorA.doSomething();

        Component decoratorB = new DecoratorBImplementation(component);
        decoratorB.doSomething();

        Component decoratorC = new DecoratorBImplementation(decoratorA);
        decoratorC.doSomething();

        // Define a skeleton of an algorithm in an operation deferring steps to the subclasses.
        // Template method design pattern lets subclasses redefine certain steps on an algorithm without changing the structure of the algorithm.
        TemplateMethodInterface templateMethodA = new TemplateMethodAImplementation();
        templateMethodA.templateMethod();

        TemplateMethodInterface templateMethodB = new TemplateMethodBImplementation();
        templateMethodB.templateMethod();
    }
}
