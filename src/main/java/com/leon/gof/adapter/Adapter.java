package com.leon.gof;

public class Adapter implements Target
{
    private Adaptee adaptee;

    public Adapter()
    {
        adaptee = new Adaptee();
    }

    public void request()
    {
        System.out.print("Adapter::request calls ");
        adaptee.request();
    }
}
