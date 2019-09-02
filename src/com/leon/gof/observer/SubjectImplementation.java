package com.leon.gof.observer;


import java.util.HashMap;
import java.util.Map;

public class SubjectImplementation
{
    // Knows it observers. Any number of observers may observe the subject.
    //Send a notification to its observers when the state changes.
    private Map<String, ObserverInterface> observerMap = new HashMap<>();

    public void attach(ObserverInterface observer)
    {
        this.observerMap.put(observer.toString(), observer);
    }

    public void detach(ObserverInterface observer)
    {
        System.out.println("\nRemoving the observer named " + observer);
        this.observerMap.remove(observer.toString(), observer);
    }

    public void notifyObservers(String newState)
    {
        for(Map.Entry<String, ObserverInterface> entry : this.observerMap.entrySet())
        {
            entry.getValue().update(newState);
        }
    }
}
