package com.leon.gof.mediator;

// Mediators encapsulate the logic of mediation so that it is easier to understand it since it is kept in only one place.
public class MediatorImplementation implements Mediator
{
    private String message;

    public MediatorImplementation()
    {
        this.message = "Horatio's initial message";
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }
}
