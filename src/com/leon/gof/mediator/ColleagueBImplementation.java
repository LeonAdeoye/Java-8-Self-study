package com.leon.gof.mediator;

public class ColleagueBImplementation implements Colleague
{
    private Mediator mediator;

    public ColleagueBImplementation(Mediator mediator)
    {
        this.mediator = mediator;
    }

    public void sendMessage(String message)
    {
        System.out.println("Colleague B sending this message: " + message);
        this.mediator.setMessage(message);
    }

    public void receiveMessage()
    {
        System.out.println("Colleague B received this message: " + this.mediator.getMessage());
    }
}
