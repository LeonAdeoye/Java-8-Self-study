package com.leon.gof;


// the key to this pattern is an abstract command class which declares an interface for executing operations in its simplest form.
public interface Command
{
    void execute();
}
