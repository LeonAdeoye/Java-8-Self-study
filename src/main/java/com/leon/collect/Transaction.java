package com.leon.collect;

public class Transaction
{
    private final String currency;

    public Transaction(String currency)
    {
        this.currency = currency;
    }

    public String getCurrency()
    {
        return this.currency;
    }

    @Override
    public String toString()
    {
        return "Transaction{" +
                "currency='" + currency + '\'' +
                '}';
    }
}
