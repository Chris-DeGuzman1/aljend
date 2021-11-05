package edu.ics211.h04;

import java.util.Arrays;
import java.util.Comparator;

public class SymbolTable implements SymbolTableInterface {
	String[] symbols;
	int symbolcount = 0;
	// I just copied and pasted the java file for StringComparator
	StringComparator comparator = new StringComparator();

	public SymbolTable() {
		symbols = new String[1];
	}

	// copied insertionSort and its related methods from h03
	public void insertionSort(String[] data, Comparator<String> comparator) {
		for (int i = 0; i < data.length - 1; i++) {
			while (reorder(data, i, comparator)) {
				for (int j = i; j > -1; j--) {
					if (comparator.compare(data[j], data[j + 1]) > 0) {
						swapAdjacent(data, j);
					}
				}
			}
		}
	}

	// modified reorder() to check for null
	public boolean reorder(String[] data, int index, Comparator<String> comparator) {
		if (data[index + 1] == null) {
			return false;
		} else {
			if (comparator.compare(data[index], data[index + 1]) > 0) {
				swapAdjacent(data, index);
				return true;
			} else {
				return false;
			}
		}
	}

	private void swapAdjacent(String[] data, int index) throws java.lang.ArrayIndexOutOfBoundsException {
		String swap = data[index];
		data[index] = data[index + 1];
		data[index + 1] = swap;
	}

	@Override
	public int size() {
		// returns symbolcount
		return symbolcount;
	}

	@Override
	public String get(int index) {
		// just returns the value of the index of the array that is passed into the parameter
		return symbols[index];
	}

	@Override
	public boolean add(String value) {
		// first loop will check if the symbol is already in the the array and will
		// return false if it does
		for (int i = 0; i < symbols.length; i++) {
			if (symbols[i] == null) {
				break;
			} else if (symbols[i].equals(value)) {
				return false;
			}
		}
		// if the last element in the array is a null that means there is space
		if (symbols[symbols.length - 1] == null) {
			// second loop will fill the first null element or empty string and return true
			// return statement breaks the loop, so there's no worry about adding multiples
			// of value
			for (int j = 0; j < symbols.length; j++) {
				if (symbols[j] == null || symbols[j].equals("")) {
					symbols[j] = value;
					symbolcount++;
					insertionSort(symbols, comparator);
					return true;
				}
			}
			// if there's no space, the program will create a temp array initialized with 3
			// spaces more than the original array, copy the contents of the symbols array
			// and then turn the symbols array into a clone of the temp array
		} else {
			String[] temp = new String[symbols.length + 3];
			for (int k = 0; k < symbols.length; k++) {
				temp[k] = symbols[k];
			}
			symbols = temp.clone();
			for (int l = 0; l < symbols.length; l++) {
				if (symbols[l] == null) {
					symbols[l] = value;
					insertionSort(symbols, comparator);
					symbolcount++;
					return true;
				}
			}
		}
		// if the program has made it all the way here and has not done anything,
		// there's a bug and it needs to be investigated
		System.out.println("returned false where the program shouldn't ever reach, please investigate");
		return false;
	}

	@Override
	public boolean remove(String value) {
		int count = 0;
		for (int i = 0; i < symbols.length; i++) {
			// if anything gets removed from the array, it'll raise the count by one and
			// lower the symbol count by one
			if (symbols[i] == null) {
				break;
			} else {
				if (symbols[i].equals(value)) {
					symbols[i] = "";
					symbolcount--;
					count++;
				}
			}
		}
		// since the count can only go up, it'll only return false if nothing was
		// removed
		switch (count) {
		case 0:
			return false;
		default:
			return true;

		}
	}

	@Override
	public int indexOf(String value) {
		int count = 0;
		for (int i = 0; i < symbols.length; i++) {
			if (symbols[i].equals(value)) {
				count = i;
				break;
			}
		}
		return count;
	}

	@Override
	public String toString() {
		String temp = "";
		// sorts one last time before spitting out a string
		insertionSort(symbols, comparator);
		for (int i = 0; i < symbols.length; i++) {
			// when the program gets to the end of the array, it won't add the space after
			// the symbol
			if (i == symbols.length - 1) {
				temp += symbols[i];
			} else if (symbols[i].equals("")) {
				continue;
			} else if (symbols[i + 1] == null) {
				temp += symbols[i];
				break;
			} else {
				temp += symbols[i] + " ";
			}
		}
		if (temp.equals("") || temp.equals(null)) {
			return "";
		} else {
			return temp;
		}
	}

}
