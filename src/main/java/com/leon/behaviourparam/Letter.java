package com.leon.behaviourparam;

public class Letter
{
    public static String addHeader(String name)
    {
        return "from " + name;
    }

    public static String addBody(String body)
    {
        return "This what I want to say " + body;
    }

    public static String addFooter(String name)
    {
        return "Kind regards " + name;
    }
}
