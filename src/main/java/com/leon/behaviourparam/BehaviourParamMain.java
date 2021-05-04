package com.leon.behaviourparam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class BehaviourParamMain
{
    public static List<Apple> filterApples(List<Apple> apples, ApplePredicate applePredicate)
    {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : apples)
        {
            if(applePredicate.test(apple))
                result.add(apple);
        }
        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p)
    {
        List<T> result = new ArrayList<>();
        for(T s: list)
        {
            if(p.test(s))
                result.add(s);
        }
        return result;
    }

    public static <T,R> List<R> filter(List<T> list, Function<T,R> func)
    {
        List<R> result = new ArrayList<>();
        for(T s: list)
        {
            result.add(func.apply(s));
        }
        return result;
    }

    public static <T> void printItems(String property, List<T> list)
    {
        System.out.println("\nCount of '" + property + "' items: " + list.size() + ". Here is the list of them:");

        for (T s : list)
        {
            System.out.println(s.toString());
        }
    }

    public static String processFile(BufferedReaderProcessorFunction func)
    {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/test.txt")))
        {
            return func.process(bufferedReader);
        }
        catch(IOException io)
        {
            System.out.println(io.getMessage());
        }
        return null;
    }

    public static <T,R> List<R> map(List<T> list, Function<T,R> func)
    {
        List<R> result = new ArrayList<>();
        for(T t : list)
        {
            result.add(func.apply(t));
        }
        return result;
    }

    public static void main()
    {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 100));
        apples.add(new Apple("green", 150));
        apples.add(new Apple("red", 120));
        apples.add(new Apple("yellow", 170));

        printItems("green", filterApples(apples, new GreenApplePredicate()));
        // You can pass a lambda to any method that has a functional interface parameter.
        // A functional interface is an interface that specifies EXACTLY one abstract method.
        printItems("heavy", filter(apples, (Apple apple) ->  apple.getWeight() > 120));  // Implicitly returns a boolean result.
        printItems("yellow", filter(apples, (Apple apple) ->  "yellow".equalsIgnoreCase(apple.getColour())));

        System.out.println(processFile((BufferedReader br)-> "\n" + br.readLine() + "\n" + br.readLine()));
        System.out.println(processFile((BufferedReader br)-> "\nThis is the first line: " + br.readLine()));

        ArrayList<String> people = new ArrayList<>(Arrays.asList("Horatio", " ", "Harper"));
        List<String> blanks = filter(people, (String s) -> s.isEmpty());  // Predicate functional interface
        System.out.println("\nOut of " + people.size() + " items there were " + blanks.size() + " blanks!");

        List<Integer> lengths = filter(Arrays.asList("Horatio", "", "Harper"), (String s) -> s.length()); // Function functional interface passed in.
        printItems("string length", lengths);

        // IntPredicate is a specialized version of a functional interface which avoids autoboxing operations when the input/outputs are primitives.
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        System.out.println("\n1000 is an even number: " + evenNumbers.test(1000)); // No boxing occurs even though the generic type is Integer.

        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 != 0;
        System.out.println("1000 is an odd number: " + oddNumbers.test(1000)); // Boxing occurs because primitive time is boxed to explicitly declared Integer.

        // IntBinaryOperator has a single binary operator called applyAsInt representing the function descriptor (int,int) -> int.
        IntBinaryOperator sum = (int a, int b) -> a + b; // IntBinaryOperator sum = (a, b) -> a + b; primitive type of lambda parameters deduced/inferred by the compiler.
        System.out.println("\nThe sum of 5 and 10 is " + sum.applyAsInt(5,10));

        //The compiler can also deduce the type of the lambda parameter
        ToIntFunction<String> stringLengths = (s) -> s.length(); // No unboxing required even though the output generic type is an Integer.
        System.out.println("\nThe length of the string 'Horatio' is " + stringLengths.applyAsInt("Horatio"));

        int portNumber = 1337;
        Consumer<Integer> consumer = (Integer i) -> System.out.println("Port number is " + portNumber);
        consumer.accept(100);
        // Local variables referenced from a lambda expression must be explicitly declared final or effectively be final to be 'captured'.
        // portNumber = 8080;

        final int age = 6; // Local variable explicitly declared as final otherwise compile error - discouraging outer variable mutations.
        Supplier<Integer> supplier = () -> age;
        System.out.println("Horatio's age is " + supplier.get());

        // Method reference used instead of lambda expression (instance method of an arbitrary TYPE).
        ToIntFunction<String> strLen = String::length;
        System.out.println("\nThe length of the string 'Saori' is " + strLen.applyAsInt("Saori"));

        Transaction transaction = new Transaction();
        // Method reference is an instance method of the above existing instantiated object. Function returns double primitive type and accept int primitive type WITHOUT boxing.
        IntToDoubleFunction expensive = transaction::getCostInUSD;
        System.out.println("\nThe cost of this 100 pounds transaction in USD is " + expensive.applyAsDouble(100));
        // Method reference is an instance method of the class String.
        BiPredicate<List<String>,String> contains = List::contains;
        System.out.println("\nIs 'Harper' in the list of string: " + contains.test(Arrays.asList("Horatio", "Harper"), "Harper"));
        System.out.println("Is 'Saori' in the list of string: " + contains.test(Arrays.asList("Horatio", "Harper"), "Saori"));

        // Method reference is an existing default constructor of an Apple
        Supplier<Apple> appleSupplier = Apple::new;
        Apple yellowApple = appleSupplier.get();  // Equivalent to: appleSupplier = () -> new Apple();

        IntFunction<Apple> heavyAppleFunc = Apple::new;
        Apple heavyRedApple = heavyAppleFunc.apply(200); // Equivalent to: heavyAppleFunc = (weight) -> new Apple(weight); // The correct constructor is chosen.

        // public static <T,R> List<R> map(List<T> list, Function<T,R> func)
        for(Apple apple : (map(Arrays.asList(100,130,160), Apple::new))) // Second argument is equivalent to (weight) -> new Apple(weight)
        {
            System.out.println(apple);
        }

        Predicate<Apple> spoiledApplePredicate = (a) -> "brown".equalsIgnoreCase(a.getColour());
        Predicate<Apple> freshApplePredicate = spoiledApplePredicate.negate(); // Functional interface's convenience default method NEGATE.
        Predicate<Apple> premiumApplePredicate = freshApplePredicate.and(a -> a.getWeight() == 150); // Functional interface's convenience default method AND.
        Predicate<Apple> premiumBritishApplePredicate = premiumApplePredicate.or(a -> a.getWeight() >= 170); // Functional interface's convenience default method OR.
        printItems("premium apple", filter(apples, premiumBritishApplePredicate));

        // The interface default method andThen returns a function 'h' that first applies a given function 'f'
        // to an input and then applies another function 'g' to the result of that application: g(f(x)).
        // The interface default method compose acts in the opposite way: f(g(x))
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer,Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);  // g(f(x))
        Function<Integer, Integer> k = f.compose(g);  // f(g(x))
        System.out.println("\ng(f(x)) = " + h.apply(4));
        System.out.println("\nf(g(x)) = " + k.apply(4));

        Function<String, String> addBody = Letter::addBody;
        Function<String, String> letterPipeline = addBody.andThen(Letter::addFooter).andThen(Letter::addHeader);
        System.out.println("My letter: " + letterPipeline.apply("Leon"));
    }
}
