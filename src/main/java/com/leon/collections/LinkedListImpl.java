package com.leon.collections;

public class LinkedListImpl<T> implements LinkedListInterface<T>
{
	private Node<T> root;
	private int size;

	@Override
	public void add(T element)
	{
		Node<T> node = new Node<>(element);
		node.setNext(root);
		root = node;
		++size;
	}

	@Override
	public void remove(T element)
	{
		Node<T> node = root;
		Node<T> previous = root;

		while(node != null)
		{
			if(node.getData().equals(element))
			{
				if(node == root)
					root = node.getNext();
				else
					previous.setNext(node.getNext());

				--size;
				break;
			}
			previous = node;
			node = node.getNext();
		}
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	@Override
	public void traverse()
	{
		Node<T> node = root;
		while(node != null)
		{
			System.out.println(node.getData());
			node = node.getNext();
		}
	}

	public static void main()
	{
		LinkedListInterface<String> list = new LinkedListImpl<>();
		list.add("Saori");
		list.add("Harper");
		list.add("Horatio");
		list.traverse();
		list.remove("Harper");
		list.traverse();
	}
}
