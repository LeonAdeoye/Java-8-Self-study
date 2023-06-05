package com.leon.collections;

import java.util.*;

public class CollectionsMain
{
    public static void main()
    {

        List<String> staff = new LinkedList<>();
        staff.add("Saori");
        staff.add("Harper");
        staff.add("Horatio");
        ListIterator<String> iterator = staff.listIterator();
        iterator.next();

        Set<String> words = new HashSet<>();
        // Assumes that the elements you insert into implement the Comparable interface (like the String).
        Set<String> tree = new TreeSet<>();

        // If the class does not implement the Comparable interface then you can pass the TreeSet constructor  an object that implements the Comparator interface.
        Set<String> treeWithout = new TreeSet<>(new Comparator<String>()
        {
            @Override
            public int compare(String s, String t)
            {

                return s.compareToIgnoreCase(t);
            }
        });

        // You can also pass a lambda to the constructor
        Set<String> treeWithoutLambda = new TreeSet<>((a,b) -> a.compareTo(b));
    }
}
