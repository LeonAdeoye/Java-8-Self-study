package com.leon.algos;

public class BinarySearch
{
	public static int binarySearchRecursively(int[] array, int key, int low, int high)
	{
		int mid = low + ((high  - low)/2);

		if(low > high)
			return -1;

		if(array[mid] == key)
			return mid;

		if(array[mid] > key)
			return binarySearchRecursively(array, key, low, mid - 1);
		else
			return binarySearchRecursively(array, key, mid + 1, high);
	}

	public static int binarySearchIteratively(int[] array, int key, int low, int high)
	{
		while(low <= high)
		{
			int mid = low + ((high - low)/2);

			if(key > array[mid])
				low = mid + 1;
			else if(key < array[mid])
				high = mid - 1;
			else if(key == array[mid])
				return mid;
		}
		return -1;
	}

	public static void main()
	{
		System.out.println(binarySearchRecursively(new int[] {1,2,3,4,5,6}, 1, 0, 5));
		System.out.println(binarySearchRecursively(new int[] {1,2,3,4,5,6}, 2, 0, 5));
		System.out.println(binarySearchRecursively(new int[] {1,2,3,4,5,6}, 3, 0, 5));
		System.out.println(binarySearchRecursively(new int[] {1,2,3,4,5,6}, 4, 0, 5));
		System.out.println(binarySearchRecursively(new int[] {1,2,3,4,5,6}, 5, 0, 5));
		System.out.println(binarySearchRecursively(new int[] {1,2,3,4,5,6}, 6, 0, 5));
		System.out.println(binarySearchRecursively(new int[] {1,2,3,4,5,6}, 7, 0, 5));

		System.out.println(binarySearchIteratively(new int[] {1,2,3,4,5,6}, 1, 0, 5));
		System.out.println(binarySearchIteratively(new int[] {1,2,3,4,5,6}, 2, 0, 5));
		System.out.println(binarySearchIteratively(new int[] {1,2,3,4,5,6}, 3, 0, 5));
		System.out.println(binarySearchIteratively(new int[] {1,2,3,4,5,6}, 4, 0, 5));
		System.out.println(binarySearchIteratively(new int[] {1,2,3,4,5,6}, 5, 0, 5));
		System.out.println(binarySearchIteratively(new int[] {1,2,3,4,5,6}, 6, 0, 5));
		System.out.println(binarySearchIteratively(new int[] {1,2,3,4,5,6}, 7, 0, 5));
	}
}