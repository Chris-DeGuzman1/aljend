/**
 *
 */
package edu.ics211.h03;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author your name here
 *
 */
public class Sort<E> {

	int numberOfSwaps;
	int numberOfComparisons;
	boolean swapped;
	int tempSwaps;

	/**
	 * initialize the statistics
	 */
	public Sort() {
		initStats();
	}

	private void initStats() {
		numberOfSwaps = 0;
		numberOfComparisons = 0;
		tempSwaps = 0;
		swapped = true;
	}

	// get method for the number of comparisons
	public int numComparisons() {
		return numberOfComparisons;
	}

	// get method for the number of swaps
	public int numSwaps() {
		return numberOfSwaps;
	}

	private void swapAdjacent(E[] data, int index) throws java.lang.ArrayIndexOutOfBoundsException {
		numberOfSwaps++;
		E swap = data[index];
		data[index] = data[index + 1];
		data[index + 1] = swap;
	}

	public boolean reorder(E[] data, int index, Comparator<E> comparator) {
		numberOfComparisons++;
		if (comparator.compare(data[index], data[index + 1]) > 0) {
			swapAdjacent(data, index);
			return true;
		} else {
			tempSwaps++;
			return false;
		}
	}

	public void swapDistant(E[] data, int i, int j) {
		// temp holds the element at data[i], overwrites data[i] with data[j], and then
		// overwrites data[j] with temp
		E temp = data[i];
		data[i] = data[j];
		data[j] = temp;
		numberOfSwaps++;
	}

	public void bubbleSort(E[] data, Comparator<E> comparator) {
		initStats();
		while (swapped) {
			for (int i = 0; i < data.length - 1; i++) {
				if ((reorder(data, i, comparator) == false) && (tempSwaps == data.length - 1)) {
					swapped = false;
				}
			}
			tempSwaps = 0;
		}
	}

	public void insertionSort(E[] data, Comparator<E> comparator) {
		initStats();
		for (int i = 0; i < data.length - 1; i++) {
			while (reorder(data, i, comparator)) {

				for (int j = i; j > -1; j--) {
					if (comparator.compare(data[j], data[j + 1]) > 0) {
						numberOfComparisons++;
						swapAdjacent(data, j);
					}
				}
			}
		}
	}

	public void selectionSort(E[] data, Comparator<E> comparator) {
		initStats();
		for (int i = 0; i < data.length; i++) {
			// assume that the element at the current starting point of i is the smallest
			int minIndex = i;
			for (int j = i + 1; j < data.length; j++) {
				// if data[j] is smaller than data[minIndex], then the index value of j will be
				// recorded until the inner loop reaches the end of the array
				numberOfComparisons++;
				if (comparator.compare(data[minIndex], data[j]) > 0) {
					minIndex = j;
				}
			}
			// after the smallest element after data[i] is found, the program will swap
			// data[minIndex] with data[i]
			swapDistant(data, i, minIndex);
		}
	}

	public static void main(String[] args) {
		Sort<Integer> stringsorter = new Sort<Integer>();
		IntegerComparator sc = new IntegerComparator();
		Integer[] unsortedStrings = { 10, 5, 8, 7, 3, 6, 1, 4, 2, 9 };
		stringsorter.selectionSort(unsortedStrings, sc);
		System.out.println(Arrays.toString(unsortedStrings));

	}

}