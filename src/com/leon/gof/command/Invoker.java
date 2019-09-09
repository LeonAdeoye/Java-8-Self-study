package com.leon.gof.command;

// Invoker asks the command to carry out the requests.
public class Invoker
{
    private Command command;
    public void setCommand(Command command)
    {
        this.command = command;
    }
    public void execute()
    {
        command.execute();
    }
}
