package com.leon.algos;

import java.util.Arrays;

public class BubbleSort
{
	public static void sortIteratively(int[] array)
	{
		int length = array.length;
		boolean swapped;
		for(int i = 0; i < length - 1; i++)
		{
			swapped = false;
			for(int j = 0; j < length - i - 1; j++)
			{
				if(array[j] > array[j+1])
				{
					int temp = array[j+1];
					array[j+1] = array[j];
					array[j] = temp;
					swapped = true;
				}
			}
			if(!swapped)
				break;
		}
	}

	public static void sortRecursively(int[] array, int length)
	{
		boolean sorted = true;
		for(int i = 0; i < length - 1; i++)
		{
			if(array[i] > array[i+1])
			{
				int temp = array[i+1];
				array[i+1] = array[i];
				array[i] = temp;
				sorted = false;
			}
			if(sorted == false)
				sortRecursively(array, length);

		}
	}

	public static void main()
	{
		int[] unsortedArrayONE = new int[] {2,0,1,0,5,3};
		sortIteratively(unsortedArrayONE);
		Arrays.stream(unsortedArrayONE).forEach(System.out::print);
		System.out.println("");

		int[] unsortedArrayTWO = new int[] {2,0,1,0,5,3};
		sortRecursively(unsortedArrayTWO, 6);
		Arrays.stream(unsortedArrayTWO).forEach(System.out::print);
		System.out.println("");
	}
}
