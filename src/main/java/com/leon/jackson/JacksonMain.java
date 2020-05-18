package com.leon.jackson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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

            String jsonCarArray ="[{\"colour\":\"yellow\", \"model\":\"mercedes\"}, {\"colour\":\"green\", \"model\":\"bmw\"}]";
            List<Car> listOfCars = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>() {});
            listOfCars.forEach(System.out::println);

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

            json = "{\"colour\":\"blue\", \"model\":\"nissan\", \"owner\":\"Leon\"}";
            Car nissan = objectMapper.readValue(json, Car.class);
            System.out.println(nissan);

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
            objectMapper.setDateFormat(df);
            // Use date format with car objects that have a field with is set to epochTime

        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }
}
