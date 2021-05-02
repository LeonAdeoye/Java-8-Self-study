package com.leon;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MapExample
{
    public static void main()
    {
        Map<String, String> books = new HashMap<>();
        books.put("Dickens", "Oliver Twist");
        books.put("Bronte", "Jane Eyre");
        books.put("Hugo", "Les Miserables");

        String aBook = books.getOrDefault("Shakespeare", "Merchant of Venice");
        Function<String, String> result = (k) -> k + " book";
        books.computeIfAbsent("Standahl", result);
        System.out.println(books.get("Standahl"));

        BiFunction<String, String, String> biresult = (k, y) -> k + "'s " + y + " was a good book!";
        books.computeIfPresent("Dickens", biresult);
        System.out.println(books.get("Dickens"));

        // If the given key is present in the map then return the value and leave the map untouched.
        books.putIfAbsent("Dickens", "David Copperfield");
        // If the given key is absent in the map then adds the new key and value and returns null.
        books.putIfAbsent("Dumas", "Count of Montecristo");

        books.replace("Standahl", "The Red and the black");

        System.out.println(books);
    }
}
