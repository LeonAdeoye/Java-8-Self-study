package com.leon.collect;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class CollectMain
{
    private static Map<String, List<Transaction>> createMapOfCurrecnces(List<Transaction> transactions)
    {
        Map<String, List<Transaction>> mapOfCurrencies = new HashMap<>();

        for(Transaction transaction : transactions)
        {
            String currency = transaction.getCurrency();
            List<Transaction> transactionForCurrency = mapOfCurrencies.get(currency);

            if(transactionForCurrency == null)
            {
                transactionForCurrency = new ArrayList<>();
                mapOfCurrencies.put(currency, transactionForCurrency);
            }

            transactionForCurrency.add(transaction);
        }

        return mapOfCurrencies;
    }

    public static void main()
    {
        List<Transaction> transactions = Arrays.asList(new Transaction("USD"), new Transaction("GBP"), new Transaction("JPY"));

        Map<String, List<Transaction>> mapOfCurrencies1 = createMapOfCurrecnces(transactions);
        System.out.println("\nFirst map size: " + mapOfCurrencies1.size());

        // groupingBy create a map whose keys are the buckets and whose values are a list of elements in those buckets.
        Map<String, List<Transaction>> mapOfCurrencies2 = transactions.stream().collect(groupingBy(Transaction::getCurrency));
        System.out.println("Second map size: " + mapOfCurrencies2.size());
    }
}
