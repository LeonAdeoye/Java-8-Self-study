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
    }
}
