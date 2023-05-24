package com.leon.algos;

import java.util.Arrays;

public class MergeSort
{
	public static void mergeSort(int[] parentArray)
	{
		// Cache the length of the parent array.
		int length = parentArray.length;

		// The recursion base case is when there is only one element in the array.
		// When you reach this point it is time to merge this element into the parent array.
		// And also copy whatever is left in the other array.
		if(length <= 1)
			return;

		// Cache the middle of the array.
		int middle = length/2;

		// Create the left and the right array.
		int[] leftArray = new int[middle];
		int[] rightArray = new int[length - middle];

		// Copy all parent array elements on the left-hand-side of middle to the left array.
		for(int leftArrayIndex = 0; leftArrayIndex < leftArray.length; leftArrayIndex++)
		{
			leftArray[leftArrayIndex] = parentArray[leftArrayIndex];
		}

		// Copy all parent array elements from the middle and right-hand-side to the right array.
		for(int rightArrayIndex = 0; rightArrayIndex < rightArray.length; rightArrayIndex++)
		{
			rightArray[rightArrayIndex] = parentArray[middle + rightArrayIndex];
		}

		// Recursively call merge sort on both the left and the right array to divide and conquer.
		mergeSort(leftArray);
		mergeSort(rightArray);
		// Merge the two arrays into the parent array.
		merge(leftArray, rightArray, parentArray);
	}

	private static void merge(int[] leftArray, int[] rightArray, int[] parentArray)
	{
		// Cache the length of both the left and the right array.
		int leftSize = leftArray.length;
		int rightSize = rightArray.length;

		// You need three integer variables for the indexes of the left, right and parent arrays.
		int i = 0, l = 0, r = 0;

		// While the left index is less than the size of the left array and the right index is less than the size of the right array.
		while(l < leftSize && r < rightSize)
		{
			// If the current left array value is less than the current right array value then copy the left array value to the parent array
			// Otherwise copy the right array value. After the copy, increment the relevant array index and the parent arrayt index.
			if(leftArray[l] < rightArray[r])
				parentArray[i++] = leftArray[l++];
			else
				parentArray[i++] = rightArray[r++];
		}

		// If the left array index is less than the size of the left array then copy the remaining elements to the parent array.
		while(l < leftSize)
		{
			parentArray[i++] = leftArray[l++];
		}

		// If the right array index is less than the size of right array then copy the remaining elements from the right array.
		while(r < rightSize)
		{
			parentArray[i++] = rightArray[r++];
		}
	}

	public static void main()
	{
		int[] parentArray = new int[] {8, 2, 0, 0, 7, 6, 1};
		mergeSort(parentArray);
		Arrays.stream(parentArray).forEach(System.out::print);
	}
}
