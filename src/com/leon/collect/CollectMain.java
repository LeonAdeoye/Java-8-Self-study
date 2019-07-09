package com.leon.collect;

import com.leon.streams.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

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
        List<Dish> menu = asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<Transaction> transactions = Arrays.asList(new Transaction("USD"), new Transaction("GBP"), new Transaction("JPY"));

        Map<String, List<Transaction>> mapOfCurrencies1 = createMapOfCurrecnces(transactions);
        System.out.println("\nFirst map size: " + mapOfCurrencies1.size());

        // groupingBy create a map whose keys are the buckets and whose values are a list of elements in those buckets.
        Map<String, List<Transaction>> mapOfCurrencies2 = transactions.stream().collect(groupingBy(Transaction::getCurrency));
        System.out.println("Second map size: " + mapOfCurrencies2.size());

        System.out.println("Using collect(Collectors.counting), the dish count is " + menu.stream().collect(counting()));
        System.out.println("Using Stream.count, the dish count is " + menu.stream().count());

        //maxBy and minBy take comparators in order to determine element with max or min values.
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        menu.stream().collect(maxBy(dishCaloriesComparator)).ifPresent(d -> System.out.println("Most calorie dish: " + d));

        //The Collectors class provides a specific factory method for summing: Collectors.summarizingInt.
        // It accepts a function that maps an object into the int that has to be summarized and returns a
        // collector that, when passed to the usual collect method, performs the requested summarization.
        System.out.println("Calorie: " + menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)));

        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories)); // This just get the total
        double averageCalories = menu.stream().collect(averagingInt(Dish::getCalories)); //This get the average and return double.
    }
}
