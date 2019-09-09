package com.leon.gof.command;

// Encapsulate a request in a object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.
// Command implementation subclasses specify a 'receiver-action' pair by storing the receiver as an instance variable and by implementing an execute function to invoke the request.
// The command pattern decouples the object that invokes the operation from the one having the knowledge to perform the action.

public class CommandImplementation implements Command
{
    private Receiver receiver;

    public CommandImplementation(Receiver receiver)
    {
        this.receiver = receiver;
    }

    public void execute()
    {
        receiver.action();
    }
}
