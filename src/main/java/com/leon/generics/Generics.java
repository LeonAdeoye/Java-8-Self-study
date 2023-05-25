package com.leon.generics;

import java.util.*;
import java.util.stream.IntStream;

public class Generics
{
	// <T> is the simple generic type
	// <?> is the non-bounded wildcard
	// <? extends T> upper bounded wildcard
	// <? super T> lower bounded wildcard

	// Covariance: if we have a type T, only T and the subtypes of T are allowed in the context. <? extends T>
	// Invariance: if we have a type T, only T is allowed in the context. <T>
	// Contravariance: if we have a type T, only T and the super types of T are allowed in the context. <? super T>

	// Collections are invariant but collections plus wildcards provide compile-safety and the covariance flexibility of arrays.

	public static double sum(Collection<Number> numbers)
	{
		return numbers.stream().mapToDouble(Number::doubleValue).sum();
	}

	// Upper bounded wildcards are used when we need to retrieve elements from a collection and not when we need to alter/extend/add the collection.
	public static double sumWithCovariance(Collection<? extends Number> numbers)
	{
		return numbers.stream().mapToDouble(Number::doubleValue).sum();
	}

	public static void mainCovariance()
	{
		List<Integer> integers = Arrays.asList(1, 2, 3);
		List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);
		// Below won't compile because collections are invariant. You need to make them covariant by using an upper bounded wildcard.
		// double sum = sum(integers);
		// double sum = sum(doubles);
		double sum = sumWithCovariance(integers);
	}

	//////////////////////////////////////////////////////////////////////

	public static void appendWithContravariance(Collection<? super Integer> integers)
	{
		IntStream.rangeClosed(1,5).forEach(integers::add);
	}

	public static void append(Collection<Integer> integers)
	{
		IntStream.rangeClosed(1,5).forEach(integers::add);
	}

	public static void mainContravariance()
	{
		List<Number> numbers = new ArrayList<>();
		numbers.add(3.14);
		numbers.add(42);
		// Below won't work because
		// append(numbers);
		appendWithContravariance(numbers);
	}
}
