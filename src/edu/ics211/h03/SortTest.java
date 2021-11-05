/**
 * 
 */
package edu.ics211.h03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author esb
 * @author your name here
 *
 */
class SortTest {

	private Sort<String> stringSorter;
	private Sort<Integer> integerSorter;
	private StringComparator sc;
	// students must create integerComparator, then uncomment the next line
	private IntegerComparator ic;
	static final String[] unsortedStrings = { "hello", "world", "ics", "211", "true", "yes" };
	static final String[] sortedStrings = { "211", "hello", "ics", "true", "world", "yes" };
	static final Integer[] unsortedIntegers = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3 };
	static final Integer[] sortedIntegers = { 1, 1, 2, 3, 3, 4, 5, 5, 6, 9 };
	static Integer[] manyIntegers;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		stringSorter = new Sort<String>();
		integerSorter = new Sort<Integer>();
		sc = new StringComparator();
		ic = new IntegerComparator();
		Random rand = new Random();
		manyIntegers = new Integer[rand.nextInt(1000)];
		for (int i = 0; i < manyIntegers.length; i++) {
			manyIntegers[i] = rand.nextInt(10000);
		}
	}

	private void assertArraysEqual(Object[] a1, Object[] a2) {
		assertEquals(a1.length, a2.length);
		for (int i = 0; i < a1.length; i++) {
			assert (a1[i].equals(a2[i]));
		}
	}

	private void testStringSorts(String[] original, String[] expected) {
		final boolean printSorted = true; // make true for more debugging
		assertEquals(stringSorter.numComparisons(), 0);
		assertEquals(stringSorter.numSwaps(), 0);
		// first: bubble sort
		String[] sorted = Arrays.copyOf(original, original.length);
		stringSorter.bubbleSort(sorted, sc);
		System.out.println("bubble sort on an array of length " + original.length + " did "
				+ stringSorter.numComparisons() + " comparisons and " + stringSorter.numSwaps() + " swaps");
		assertArraysEqual(sorted, expected);
		assert (stringSorter.numComparisons() > 0);
		assert (stringSorter.numSwaps() > 0);
		assert (stringSorter.numComparisons() >= stringSorter.numSwaps());
		// sort again, make sure the statistics are reset correctly
		int swaps = stringSorter.numSwaps();
		int comparisons = stringSorter.numComparisons();
		sorted = Arrays.copyOf(original, original.length);
		// bubbleSort is called again, but does not do another sort
		stringSorter.bubbleSort(sorted, sc);
		assertEquals(stringSorter.numComparisons(), comparisons);
		assertEquals(stringSorter.numSwaps(), swaps);
		for (int i = 0; i + 1 < sorted.length; i++) {
			if (printSorted) {
				System.out.println("sorted[" + i + "] is " + sorted[i]);
			}
			assert (sc.compare(sorted[i], sorted[i + 1]) <= 0);
		}
		if (printSorted) {
			System.out.println("sorted[" + (sorted.length - 1) + "] is " + sorted[sorted.length - 1]);
		}
		// next: insertion sort
		sorted = Arrays.copyOf(original, original.length);
		stringSorter.insertionSort(sorted, sc);
		System.out.println("insertion sort on an array of length " + original.length + " did "
				+ stringSorter.numComparisons() + " comparisons and " + stringSorter.numSwaps() + " swaps");
		assert (stringSorter.numComparisons() > 0);
		assert (stringSorter.numSwaps() > 0);
		assert (stringSorter.numComparisons() >= stringSorter.numSwaps());
		// sort again, make sure the statistics are reset correctly
		swaps = stringSorter.numSwaps();
		comparisons = stringSorter.numComparisons();
		sorted = Arrays.copyOf(original, original.length);
		stringSorter.insertionSort(sorted, sc);
		assertEquals(stringSorter.numComparisons(), comparisons);
		assertEquals(stringSorter.numSwaps(), swaps);
		for (int i = 0; i + 1 < sorted.length; i++) {
			if (printSorted) {
				System.out.println("sorted[" + i + "] is " + sorted[i]);
			}
			assert (sc.compare(sorted[i], sorted[i + 1]) <= 0);
		}
		if (printSorted) {
			System.out.println("sorted[" + (sorted.length - 1) + "] is " + sorted[sorted.length - 1]);
		}
		// finally, selection sort
		sorted = Arrays.copyOf(original, original.length);
		stringSorter.selectionSort(sorted, sc);
		System.out.println("selection sort on an array of length " + original.length + " did "
				+ stringSorter.numComparisons() + " comparisons and " + stringSorter.numSwaps() + " swaps");
		assert (stringSorter.numComparisons() > 0);
		assert (stringSorter.numSwaps() > 0);
		assert (stringSorter.numComparisons() >= stringSorter.numSwaps());
		// sort again, make sure the statistics are reset correctly
		swaps = stringSorter.numSwaps();
		comparisons = stringSorter.numComparisons();
		sorted = Arrays.copyOf(original, original.length);
		stringSorter.selectionSort(sorted, sc);
		assertEquals(stringSorter.numComparisons(), comparisons);
		assertEquals(stringSorter.numSwaps(), swaps);
		for (int i = 0; i + 1 < sorted.length; i++) {
			if (printSorted) {
				System.out.println("sorted[" + i + "] is " + sorted[i]);
			}
			assert (sc.compare(sorted[i], sorted[i + 1]) <= 0);
		}
		if (printSorted) {
			System.out.println("sorted[" + (sorted.length - 1) + "] is " + sorted[sorted.length - 1]);
		}
	}

	/**
	 * @param original: data to be sorted
	 * @param expected: same data, already sorted. If expected is null, the data
	 *                  sorted by insertion sort and selection sort is compared to
	 *                  the data sorted by bubble sort, reporting success only if
	 *                  all three are the same
	 */
	private void testIntegerSorts(Integer[] original, Integer[] expected) {
		final boolean printSorted = false; // make true for more debugging
		// commented these assertions so that the two integer tests can run one right after the other
		// assertEquals(integerSorter.numComparisons(), 0);
		// assertEquals(integerSorter.numSwaps(), 0);
		// first: bubble sort
		Integer[] sorted = Arrays.copyOf(original, original.length);
		integerSorter.bubbleSort(sorted, ic);
		System.out.println("bubble sort on an array of length " + original.length + " did "
				+ integerSorter.numComparisons() + " comparisons and " + integerSorter.numSwaps() + " swaps");
		// commented the comparison for sorted and expected because the manyIntegers call passes in a null object
		// assertArraysEqual(sorted, expected);
		assert (integerSorter.numComparisons() > 0);
		assert (integerSorter.numSwaps() > 0);
		assert (integerSorter.numComparisons() >= integerSorter.numSwaps());
		// sort again, make sure the statistics are reset correctly
		int swaps = integerSorter.numSwaps();
		int comparisons = integerSorter.numComparisons();
		sorted = Arrays.copyOf(original, original.length);
		// bubbleSort is called again, but does not do another sort
		integerSorter.bubbleSort(sorted, ic);
		assertEquals(integerSorter.numComparisons(), comparisons);
		assertEquals(integerSorter.numSwaps(), swaps);
		for (int i = 0; i + 1 < sorted.length; i++) {
			if (printSorted) {
				System.out.println("sorted[" + i + "] is " + sorted[i]);
			}
			assert (ic.compare(sorted[i], sorted[i + 1]) <= 0);
		}
		if (printSorted) {
			System.out.println("sorted[" + (sorted.length - 1) + "] is " + sorted[sorted.length - 1]);
		}
		// next: insertion sort
		sorted = Arrays.copyOf(original, original.length);
		integerSorter.insertionSort(sorted, ic);
		System.out.println("insertion sort on an array of length " + original.length + " did "
				+ integerSorter.numComparisons() + " comparisons and " + integerSorter.numSwaps() + " swaps");
		assert (integerSorter.numComparisons() > 0);
		assert (integerSorter.numSwaps() > 0);
		assert (integerSorter.numComparisons() >= integerSorter.numSwaps());
		// sort again, make sure the statistics are reset correctly
		swaps = integerSorter.numSwaps();
		comparisons = integerSorter.numComparisons();
		sorted = Arrays.copyOf(original, original.length);
		integerSorter.insertionSort(sorted, ic);
		assertEquals(integerSorter.numComparisons(), comparisons);
		assertEquals(integerSorter.numSwaps(), swaps);
		for (int i = 0; i + 1 < sorted.length; i++) {
			if (printSorted) {
				System.out.println("sorted[" + i + "] is " + sorted[i]);
			}
			assert (ic.compare(sorted[i], sorted[i + 1]) <= 0);
		}
		if (printSorted) {
			System.out.println("sorted[" + (sorted.length - 1) + "] is " + sorted[sorted.length - 1]);
		}
		// finally, selection sort
		sorted = Arrays.copyOf(original, original.length);
		integerSorter.selectionSort(sorted, ic);
		System.out.println("selection sort on an array of length " + original.length + " did "
				+ integerSorter.numComparisons() + " comparisons and " + integerSorter.numSwaps() + " swaps");
		assert (integerSorter.numComparisons() > 0);
		assert (integerSorter.numSwaps() > 0);
		assert (integerSorter.numComparisons() >= integerSorter.numSwaps());
		// sort again, make sure the statistics are reset correctly
		swaps = integerSorter.numSwaps();
		comparisons = integerSorter.numComparisons();
		sorted = Arrays.copyOf(original, original.length);
		integerSorter.selectionSort(sorted, ic);
		assertEquals(integerSorter.numComparisons(), comparisons);
		assertEquals(integerSorter.numSwaps(), swaps);
		for (int i = 0; i + 1 < sorted.length; i++) {
			if (printSorted) {
				System.out.println("sorted[" + i + "] is " + sorted[i]);
			}
			assert (ic.compare(sorted[i], sorted[i + 1]) <= 0);
		}
		if (printSorted) {
			System.out.println("sorted[" + (sorted.length - 1) + "] is " + sorted[sorted.length - 1]);
		}
	}

	@Test
	void test() {
		assert (stringSorter != null);
		testIntegerSorts(unsortedIntegers, sortedIntegers);
		testIntegerSorts(manyIntegers, null);
		testStringSorts(unsortedStrings, sortedStrings);
	}

}