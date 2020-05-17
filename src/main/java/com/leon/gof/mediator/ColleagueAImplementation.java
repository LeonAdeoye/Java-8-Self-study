package com.leon.gof;

// The colleague classes are decoupled, adding a new colleague does not impact mediation.
public class ColleagueAImplementation implements Colleague
{
    private Mediator mediator;

    public ColleagueAImplementation(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public void sendMessage(String message)
    {
        System.out.println("Colleague A sending this message: " + message);
        this.mediator.setMessage(message);
    }

    public void receiveMessage()
    {
        System.out.println("Colleague A received this message: " + this.mediator.getMessage());
    }
}
