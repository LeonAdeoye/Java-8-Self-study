package com.leon.gof;

import com.leon.gof.adapter.Adapter;
import com.leon.gof.adapter.Target;
import com.leon.gof.bridge.*;
import com.leon.gof.command.Command;
import com.leon.gof.command.CommandImplementation;
import com.leon.gof.command.Invoker;
import com.leon.gof.command.Receiver;
import com.leon.gof.decorator.*;
import com.leon.gof.facade.Facade;
import com.leon.gof.mediator.*;
import com.leon.gof.observer.*;
import com.leon.gof.singleton.Singleton;
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
        new TemplateMethodAImplementation().templateMethod();

        new TemplateMethodBImplementation().templateMethod();

        // Defines a higher-level interface that makes the subsystems easier to use.
        // Client of the facade don't have to access the sub system objects directly.
        new Facade().doSomething();

        // Converts the interface of a class into another interface clients expect.
        // Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.
        // Target defines the interface that a client uses, Adaptee defines and existing interface that needs to be adapted, and Adapter adapts the interface of Adaptee to the Target interface.
        Target adapter = new Adapter();
        adapter.request();

        // Ensure a class only has one instance, and provide a global point of access to it.
        // The class itself is responsible for keeping track of it sole instance.
        // The class can ensure that no other instances can be created (by intercepting requests to create new objects), and provide a way to access to access to single instances.
        Singleton singleton = Singleton.getInstance();
        singleton.doSomething();

        // Defines an object that encapsulates how a set of objects interact.
        // Mediator promotes loose coupling by keeping objects from referring to each other explicitly, and lets you vary their interaction independently.
        Mediator mediator = new MediatorImplementation(); // Defines the mediator interface enabling a community of colleague objects to interact.
        Colleague a = new ColleagueAImplementation(mediator); // Each colleague knows its mediator objects.
        // The colleague classes are decoupled, adding a new colleague does not impact mediation.
        Colleague b = new ColleagueBImplementation(mediator);

        // Each colleague communicates with its mediator whenever it would have otherwise communicated with another colleague directly.
        a.sendMessage("Hi Horatio");
        b.receiveMessage();

        // Reduces communication channels between colleagues since a colleague only has to communicate with the mediator.
        b.sendMessage("Hi Harper");
        a.receiveMessage();

        // Decouples an abstraction from it implementation so that the two can vary independently.
        // Divides the abstraction and the implementation into separate class hierarchies.
        // Changes in the implementation of an abstraction should not have an impact on clients.
        Implementor impA = new ImplementorA();
        Implementor impB = new ImplementorB();

        // Implementation of an abstraction can be configured at runtime.
        Client clientA = new Client(new AbtractionImplementation(impA));
        clientA.doSomething();

        Client clientB = new Client(new AbtractionImplementation(impB));
        clientB.doSomething();

        // Command pattern decouples the object that invokes the operation from the one that knows how to perform it.
        Receiver receiver = new Receiver();
        // Client create a command implementation and specifies a receiver.
        Command command = new CommandImplementation(receiver);
        Invoker invoker = new Invoker();
        // Invoker stores the command object.
        invoker.setCommand(command);
        // Invoker issues a request by calling Execute on the command.
        invoker.execute();

    }
}
