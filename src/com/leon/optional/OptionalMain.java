package com.leon.optional;

import java.util.Optional;

public class OptionalMain
{
    public static void main()
    {
        // The static factory method Optional.empty returns an empty optional.
        Optional<Car> optCar = Optional.empty();

        // The static factory method Optional.of returns an optional from a non-null value.
        Optional<Car> car = Optional.of(new Car());
    }
}
