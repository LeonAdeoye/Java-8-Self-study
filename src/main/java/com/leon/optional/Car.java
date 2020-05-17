package com.leon.optional;

import java.util.Optional;

public class Car
{
    // The fact that a car references an Optional<Insurance> makes it explicit that a car might or might not have insurance.
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance()
    {
        return this.insurance;
    }

    @Override
    public String toString()
    {
        return "Car {" +
                "insurance=" + insurance +
                '}';
    }
}
