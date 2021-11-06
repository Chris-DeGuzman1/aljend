package edu.ics211.h05;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortedLinkedListTest<E> {
	Random rand = new Random();
	private StringComparator scomparator;
	private IntegerComparator icomparator;
	private DoubleComparator dcomparator;
	private SortedLinkedList<String> StringList;
	private SortedLinkedList<Integer> IntegerList;
	private SortedLinkedList<Double> DoubleList;
	private String[] unsortedStrings = { "S", "X", "C", "K", "T", "Y", "Z", "A", "J", "Q", "P", "U", "H", "G", "F", "V",
			"W", "D", "M", "I", "B", "E", "N", "R", "L", "O" };
	private Integer[] unsortedIntegers = { 89, 40, 27, 70, 93, 16, 66, 1, 81, 58 };
	private String[] expectedStrings = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	private Integer[] expectedIntegers = { 1, 16, 27, 40, 58, 66, 70, 81, 89, 93 };
	private double[] DoubleValues = { 0.0, 1.0, 2.0, Math.E, Math.PI, 10.0 };
	private double[] unsortedDoubles = new double[10];
	private double[] doubleValues = {  10.0, Math.E, 1.0, Math.E, 2.0,Math.PI, 10.0,Math.PI,1.0, 0.0 };

	@BeforeEach
	void setUp() throws Exception {
		for(int i = 0; i<unsortedDoubles.length; i++) {
			unsortedDoubles[i] = rand.nextDouble();
		}
		scomparator = new StringComparator();
		icomparator = new IntegerComparator();
		dcomparator = new DoubleComparator();
		StringList = new SortedLinkedList<String>(scomparator);
		IntegerList = new SortedLinkedList<Integer>(icomparator);
		DoubleList = new SortedLinkedList<Double>(dcomparator);
	}

	private void StringTest(String[] unsorted, String[] expected, SortedLinkedList<String> list) {
		assert (list.head() == null);
		for (int i = 0; i < unsorted.length; i++) {
			list.add(unsorted[i]);
		}
		assert (list.size() == 26);
		String expectedString = "";
		for (String i : expected) {
			expectedString += i + " ";
		}
		System.out.println(expectedString);
		System.out.println(list.toString());
		assertEquals(expectedString, list.toString());
	}

	private void IntegerTest(Integer[] unsorted, Integer[] expected, SortedLinkedList<Integer> list) {
		assert (list.head() == null);
		for (int i = 0; i < unsorted.length; i++) {
			list.add(unsorted[i]);
		}
		assert (list.size() == 10);
		String expectedString = "";
		for (Integer i : expected) {
			expectedString += i + " ";
		}
		System.out.println(expectedString);
		System.out.println(list.toString());
		assertEquals(expectedString, list.toString());
	}

	private void DoubleTest(double[] unsorted, SortedLinkedList<Double> list) {
		assert (list.head() == null);
		double val = 0;
		for (int i = 0; i < unsorted.length; i++) {
			list.add(unsorted[i]);
		}
		for(double i: list) {
			val += i;
		}
		System.out.println(val);
		System.out.println(list.toString());
		assert (list.size() <= 6);
	}

	private void IteratorTest() {
		Iterator<String> odd = StringList.oddIterator();
		Iterator<String> even = StringList.evenIterator();

		String expectedString = "";
		for (int y = 1; y < expectedStrings.length; y += 2) {
			expectedString += expectedStrings[y] + " ";
		}
		String oddString = "";
		for (int x = 0; x <= StringList.size() - 1; x += 2) {
			if (odd.hasNext()) {
				oddString += odd.next() + " ";
			}
		}
		assertEquals(expectedString, oddString);
		expectedString = "";
		for (int y = 0; y < expectedStrings.length; y += 2) {
			expectedString += expectedStrings[y] + " ";
		}
		String evenString = "";
		for (int x = 0; x <= StringList.size()-1; x+=2) {
			if (even.hasNext()) {
				evenString += even.next() + " ";
			}
		}
		assertEquals(expectedString, evenString);
	}

	@Test
	void test() {
		StringTest(unsortedStrings, expectedStrings, StringList);
		IntegerTest(unsortedIntegers, expectedIntegers, IntegerList);
		DoubleTest(doubleValues, DoubleList);
		IteratorTest();
	}

}
