package com.leon.optional;

import java.util.Optional;

public class OptionalMain
{
    public static void main()
    {
        // The static factory method Optional.empty returns an empty optional.
        Optional<Car> optCar = Optional.empty();

        // The static factory method Optional.of returns an optional from a non-null value.
        //If car was null, a NullPointerException would be thrown.
        Optional<Car> car = Optional.of(new Car());

        Car nullCar = null;
        // Both are null so the resulting Optional object is empty.
        Optional<Car> optionalNullCar1 = Optional.ofNullable(nullCar);
        Optional<Car> optionalNullCar2 = Optional.ofNullable(null);

        // Provide default value in the event that the optional is empty.
        Car porsche = optionalNullCar2.orElse(new Car());

        // lazy counterpart of orElse because the supplier is involved only if the optional is empty.
        Car ferrari = optionalNullCar2.orElse(new Car());

        // only executes consumer if Optional is not empty.
        optionalNullCar1.ifPresent(System.out::println);
        car.ifPresent(System.out::println);

    }
}
