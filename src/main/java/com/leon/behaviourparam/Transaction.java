package com.leon.behaviourparam;

public class Transaction
{
    private double fxRate = 1.4;
    public double getCostInUSD(int costInGBP) { return costInGBP * this.fxRate; }
}
