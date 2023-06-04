package com.leon.collections;

public class HashTable
{
	private double loadFactor = 0.75;

	public HashTable(double loadFactor)
	{
		this.loadFactor = loadFactor;
	}

	public HashTable()
	{
		loadFactor = 0.75;
	}

	public void put(String key, String value)
	{

	}

	public String get(String key)
	{
		return null;
	}

	public void remove(String key)
	{

	}

	public boolean containsKey(String key)
	{
		return false;
	}

	private int hash(String key)
	{
		return 0;
	}

	boolean isEmpty()
	{
		return false;
	}
}
