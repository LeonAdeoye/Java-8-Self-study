package com.leon.collections;

import org.jetbrains.annotations.NotNull;
import java.lang.reflect.Array;
import java.util.*;

public class HashTable<K,V>
{
	private final class HashItem<K,V>
	{
		private final K key;
		private V value;
		private HashItem<K,V> next;

		public HashItem(K key, V value)
		{
			this.key = key;
			this.value = value;
			this.next = null;
		}

		public HashItem()
		{
			this.key = null;
			this.value = null;
			this.next = null;
		}

		public K getKey()
		{
			return key;
		}

		public V getValue()
		{
			return value;
		}

		public boolean hasNext()
		{
			return next != null;
		}

		@Override
		public String toString()
		{
			return "HashItem{" + "key=" + key + ", value=" + value + '}';
		}
	}

	private final double loadFactor;
	private final int capacity;
	private int size;
	private final List<HashItem<K,V>> table;

	public HashTable(int capacity, double loadFactor)
	{
		this.loadFactor = loadFactor;
		this.capacity = capacity;
		this.table = Arrays.asList((HashItem<K, V>[]) Array.newInstance(HashItem.class, capacity));
		this.size = 0;
	}

	public HashTable()
	{
		loadFactor = 0.75;
		capacity = 10;
		this.table = Arrays.asList((HashItem<K, V>[]) Array.newInstance(HashItem.class, capacity));
		this.size = 0;
	}

	private int hash(@NotNull K key)
	{
		return key.hashCode() % capacity;
	}

	public void put(K key, V value)
	{
		int index = hash(key);
		HashItem<K,V> item = table.get(index);
		HashItem<K,V> previous = null;

		while(item != null)
		{
			if(item.getKey().equals(key))
			{
				item.value = value;
				return;
			}
			else
			{
				previous = item;
				item = item.next;
			}
		}

		item = new HashItem<>(key, value);

		if(previous == null)
			table.set(index, item);
		else
			previous.next = item;

		size++;
	}

	public V get(K key)
	{
		int index = hash(key);
		HashItem<K,V> item = table.get(index);

		while(item != null)
		{
			if(item.getKey().equals(key))
				return item.getValue();

			item = item.next;
		}

		return null;
	}

	public void remove(K key)
	{
		int index = hash(key);
		HashItem<K,V> item = table.get(index);
		HashItem<K,V> root = table.get(index);
		HashItem<K,V> previous = null;

		while(item != null)
		{
			if(item.getKey().equals(key))
			{
				if(item == root)
					root = item.next;
				else
					previous.next=item.next;

				--size;
				break;
			}
			previous = item;
			item = item.next;
		}
	}

	public boolean containsKey(K key)
	{
		int index = hash(key);
		HashItem<K,V> item = table.get(index);
		while(item != null)
		{
			if(item.getKey().equals(key))
				return true;

			item = item.next;
		}
		return false;
	}

	boolean isEmpty()
	{
		return size == 0;
	}

	public void clear()
	{
		table.clear();
		size = 0;
	}

	public int size()
	{
		return size;
	}

	public boolean containsValue(V value)
	{
		for(HashItem<K,V> item : table)
		{
			while(item != null)
			{
				if(item.getValue().equals(value))
					return true;

				item = item.next;
			}
		}
		return false;
	}

	public Collection<V> values()
	{
		List<V> values = new ArrayList<>();
		for(HashItem<K,V> item : table)
		{
			while(item != null)
			{
				values.add(item.getValue());
				item = item.next;
			}
		}
		return values;
	}

	public Set<K> keySet()
	{
		return table.stream().collect(HashSet::new, (set, item) ->
		{
			while(item != null)
			{
				set.add(item.getKey());
				item = item.next;
			}
		}, HashSet::addAll);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(HashItem<K,V> item : table)
		{
			sb.append("Bucket: ").append(table.indexOf(item)).append("\n");
			while(item != null)
			{
				sb.append(item.toString()).append("\n");
				item = item.next;
			}
		}
		return sb.toString();
	}

	public static void main()
	{
		HashTable<String, String> hashTable = new HashTable<>(3, 0.75);
		hashTable.put("1", "Leon");
		hashTable.put("2", "saori");
		hashTable.put("2", "Mike");
		hashTable.put("3", "Harper");
		hashTable.put("3", "Horatio");
		hashTable.put("5", "Isaac");

		hashTable.remove("5");
		System.out.println(hashTable);

		System.out.println("hashTable.containsKey(1): " + hashTable.containsKey("1"));
		System.out.println("hashTable.containsKey(5): " + hashTable.containsKey("5"));

		System.out.println("hashTable.containsValue(Harper): " + hashTable.containsValue("Harper"));
		System.out.println("hashTable.containsValue(Isaac): " + hashTable.containsValue("Isaac"));
		System.out.println("hashTable.containsValue(Leon): " + hashTable.containsValue("Leon"));

		hashTable.values().forEach(System.out::println);
		hashTable.keySet().forEach(System.out::println);
	}
}
