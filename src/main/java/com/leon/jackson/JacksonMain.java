package com.leon.jackson;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMain
{
    public static void main()
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            Car redBMW = new Car("red", "BMW");
            System.out.println(objectMapper.writeValueAsString(redBMW));

            String json = "{\"colour\":\"blue\", \"model\":\"toyota\"}";
            Car purpleToyota = objectMapper.readValue(json, Car.class);
            System.out.println(purpleToyota);
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
}
