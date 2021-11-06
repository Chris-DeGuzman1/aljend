package edu.ics211.h05;

public interface SortedLinkedListInterface<E> {
	  int size();
	  E get(int index);
	  // return true if the value has been added, false if it was already present
	  boolean add(E value);
	  // return true if the value has been removed, false if it was not present
	  boolean remove(E value);
	  // return the index at which the value can be found, or -1 if not found
	  int indexOf(E value);
	  // return the values with comma+blank (" ==> ") in-between
	  String toString();
	  // iterate over all values
	  java.util.Iterator iterator();
	  // iterate over even values: 0 (the first value), 2, 4, 6, etc
	  java.util.Iterator evenIterator();
	  // iterate over odd values: 1 (the second value), 3, 5, 7, etc
	  java.util.Iterator oddIterator();
}
// got your message lol