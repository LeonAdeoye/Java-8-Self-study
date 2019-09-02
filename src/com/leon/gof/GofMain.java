package com.leon.gof;

import com.leon.gof.observer.ObserverImplementation;
import com.leon.gof.observer.ObserverInterface;
import com.leon.gof.observer.SubjectImplementation;
import com.leon.gof.strategy.ContextImplementation;
import com.leon.gof.strategy.StrategyAImplementation;
import com.leon.gof.strategy.StrategyBImplementation;

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

        ObserverInterface saori = new ObserverImplementation("Saori");
        SubjectImplementation subject = new SubjectImplementation();
        subject.attach(new ObserverImplementation("Horatio"));
        subject.attach(new ObserverImplementation("Harper"));
        subject.attach(saori);
        subject.notifyObservers("Leon departed for work");
        subject.detach(saori);
        subject.notifyObservers("Leon arrived back from work");
    }
}
