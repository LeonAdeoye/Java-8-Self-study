package com.leon.jackson;

public class Car
{
    private String colour;
    private String model;

    @Override
    public String toString()
    {
        return "Car{" +
                "colour='" + colour + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    public Car(String colour, String model)
    {
        this.colour = colour;
        this.model = model;
    }

    public Car()
    {
        this.colour = "";
        this.model = "";
    }

    // Without getter and setters the jackson serialization will fail to compile
    public String getColour()
    {
        return colour;
    }

    public void setColour(String colour)
    {
        this.colour = colour;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }
}
