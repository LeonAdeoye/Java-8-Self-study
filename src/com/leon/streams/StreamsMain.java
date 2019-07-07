package com.leon.streams;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class StreamsMain
{
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

        Stream<String> dishNames = menu.stream().map(Dish::getName); // transforms a stream of dishes into a stream of string names.
        dishNames.forEach(System.out::println); // The forEach is a terminal operation that returns void.
        try
        {
            dishNames.forEach(System.out::println); // Throws an IllegalStateException because the stream has already been consumed.
        }
        catch(IllegalStateException ise)
        {
            System.out.println("IllegalStateException thrown because stream has already been consumed!");
        }

        menu.stream().filter(Dish::isVegetarian)
                .map(Dish::getName).forEach(System.out::println); // filter takes a predicate and returns another stream.
        
        asList(2,4,4,6,8,8).stream().distinct()
                .forEach(System.out::println); // unique based hashCode and equals implementation.

        asList(2,4,4,6,8,8).stream().distinct().limit(1)
                .skip(3).forEach(System.out::print);  // returns an empty stream because limited to 1 before skip.

        asList("Saori", "Leon", "Horatio", "Harper").stream().map(String::length).collect(Collectors.toList());

        // Flattens the multiple streams (Stream<String[]> generated after the call to map into a single stream.
        asList("Saori", "Leon", "Horatio", "Harper").stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::print);

        // Is there ANY dish on the menu that is vegetarian? This is terminal operation takes a predicate and returns boolean.
        if(menu.stream().anyMatch(Dish::isVegetarian)) System.out.println("\nThis menu is somewhat vegetarian friendly");

        // Are ALL dishes on the menu below 1000 calories? This is terminal operation takes a predicate and returns boolean.
        if(menu.stream().allMatch(d -> d.getCalories() < 1000))
            System.out.println("This menu is somewhat healthy because all dishes are below 1000 calories");

        if(menu.stream().noneMatch(d -> d.getCalories() > 1000))
            System.out.println("This menu is somewhat healthy because no dishes are above 1000 calories");

        // It is possible that findFirst and findAny may not return an element. Rather than return null, they return Optional<T>.
        // The Optional method ifPresent() returns true and the ifPresent(Consumer<T> block) terminal operation executes the consumer block.
        Arrays.asList(1,2,3,4,5,6,7,8).stream().map(x -> x * x).filter(x -> x % 3 == 0)
                .findFirst().ifPresent(System.out::println);

        //findFirst and findAny do not take params they rely on the filter method.
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName()));

        // Reduce terminal method takes two arguments: the initial value (below is 0),
        // and a BinaryOperator<T> to combine two elements and reduce to one: (a,b) -> (a + b)
        System.out.println("The sum of calories in the menu is " + menu.stream()
                .map(Dish::getCalories).reduce(0, (a,b) -> a + b));

        // Uses new method reference 'Integer::sum' introduced to the Integer class instead of lambda BinaryOperator<T> expression
        System.out.println("The sum of calories in vegetarian dishes is " + menu.stream().filter(Dish::isVegetarian)
                .map(Dish::getCalories).reduce(0, Integer::sum));

        // If an initial value argument is not provided to the Integer::sum reduce terminal operation then it will return an Optional.
        menu.stream().filter(d -> d.getType() == Dish.Type.MEAT).map(Dish::getCalories)
                .reduce(Integer::sum).ifPresent(d -> System.out.println("The sum of calories for the meats is " + d));

        System.out.println("The vegetarian dish with the maximum calories is " + menu.stream()
                .filter(Dish::isVegetarian).map(Dish::getCalories).reduce(0, Integer::max));

        System.out.println("The vegetarian dish with the minimum calories is " + menu.stream()
                .filter(Dish::isVegetarian).map(Dish::getCalories).reduce(Integer.MAX_VALUE, Integer::min));

        // If an initial value argument is not provided to the Integer::min reduce terminal operation (x,y) -> x < y ? x : y then it will return an Optional.
        menu.stream().filter(d -> d.getType() == Dish.Type.FISH).map(Dish::getCalories)
                .reduce(Integer::min).ifPresent(d -> System.out.println("The fish dish with the minimum calories is " + d));
    }


}
