package com.leon.behaviourparam;

public class Apple
{
    private String colour;
    private int weight;

    public String getColour()
    {
        return colour;
    }

    public void setColour(String colour)
    {
        this.colour = colour;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public Apple(String colour, int weight)
    {
        this.colour = colour;
        this.weight = weight;
    }

    public Apple()
    {
        this.colour = "yellow";
        this.weight = 90;
    }

    public Apple(int weight)
    {
        this.colour = "red";
        this.weight = weight;
    }

    @Override
    public String toString()
    {
        return "Apple { colour=" + colour + ", weight=" + weight + " }";
    }
}
