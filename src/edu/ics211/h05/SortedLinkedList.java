package edu.ics211.h05;

import java.util.Comparator;
import java.util.Iterator;

/**
 * @author Chris DeGuzman
 * @author rumeshsenthilnathan
 * 
 *         ICS 211 Homework 5
 * 
 * 
 */
public class SortedLinkedList<E> implements Iterable<E>, SortedLinkedListInterface<E> {
	private LinkedNode<E> head;
	private LinkedNode<E> tail;
	private int size;
	private Comparator<E> comp;
	private Iterator<E> iter = this.iterator();

	// LinkedNode definition taken from Edo's code
	private static class LinkedNode<E> {
		private E item;
		private LinkedNode<E> next;
		private LinkedNode<E> prev;

		private LinkedNode(E value) {
			item = value;
			next = null;
			prev = null;
		}

		private LinkedNode(E value, LinkedNode<E> reference) {
			item = value;
			next = reference;
		}
	}

	public LinkedNode<E> head() {
		return head;
	}

	public SortedLinkedList(Comparator<E> comparator) {
		head = null;
		tail = null;
		size = 0;
		comp = comparator;

	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public E get(int index) {
		LinkedNode<E> temp = head;
		E value;
		if (index > size - 1 || index < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
			value = temp.item;
			return value;
		}
	}

	@Override
	public boolean remove(E value) {
		// iterates through list. If there's a match, tail.next will link to the node
		// after, effectively removing the matched node from the list
		LinkedNode<E> temp = head;
		if (temp.item.equals(value)) {
			temp = temp.next;
			return true;
		} else {
			while (temp != null) {
				if (temp.next.item.equals(value)) {
					temp.next = temp.next.next;
					size--;
					return true;
				} else {
					tail = tail.next;
				}
			}
			return false;
		}
	}

	@Override
	public int indexOf(E value) {
		// iterates through the list, once it finds a match, it returns the index
		tail = head.next;
		int index = 0;
		while (tail.next != null) {
			if (tail.item == value) {
				return index;
			} else {
				index++;
				tail = tail.next;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		LinkedNode<E> temp = head;
		String tempStr = "";
		while (temp != null) {
			tempStr += temp.item + " ";
			temp = temp.next;
		}
		return tempStr;
	}

	@Override
	public boolean add(E value) {
		LinkedNode<E> current;
		LinkedNode<E> newNode = new LinkedNode<E>(value);

		// if the list is empty, then the both head and tail points to newNode

		if (head == null) {

			tail = head = newNode;
			size++;
			return true;
		} else {
			// theoretically sorts automatically as new nodes are added
			current = head;
			tail = head.next;
			while (current != null) {

				// if newNode belongs at the front, it becomes the new head
				if (comp.compare(newNode.item, head.item) < 0) {
					head.prev = newNode;
					newNode.next = head;
					head = newNode;
					size++;
					break;
					// since tail is one node ahead of current, if the program reaches the end
					// without inserting newNode, that means newNode belongs at the end of the list
				} else if (comp.compare(current.item, newNode.item) == 0) {
					return false;
				} else if (tail == null) {
					current.next = newNode;
					newNode.prev = current;
					size++;
					break;
				} else if (comp.compare(newNode.item, tail.item) < 0) {
					// inserts node in the correct place
					current.next = newNode;
					newNode.prev = current;
					newNode.next = tail;
					tail.prev = newNode;
					size++;
					break;

				}
				// allows the program to iterate through the list
				current = current.next;
				tail = tail.next;
			}
		}
		return true;
	}

	@Override
	public Iterator<E> iterator() {
		Iterator<E> LinkedListIterator = new Iterator<E>() {
			protected LinkedNode<E> current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public E next() {
				if (hasNext()) {
					E value = current.item;
					current = current.next;
					return value;
				}
				throw new java.util.NoSuchElementException("linked list.next");
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Linked list iterator remove not supported");
			}

		};
		return LinkedListIterator;
	}

	@Override
	public Iterator<E> evenIterator() {
		Iterator<E> evenIterator = new Iterator<E>() {
			LinkedNode<E> current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public E next() {
				E value = current.item;
				if (hasNext()) {
					if (current.next == null) {
						current.next = null;
					} else {
						current = current.next.next;
					}
				}
				return value;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Linked list iterator remove not supported");
			}

		};
		return evenIterator;
	}

	@Override
	public Iterator<E> oddIterator() {
		Iterator<E> oddIterator = new Iterator<E>() {
			LinkedNode<E> current = head.next;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public E next() {
				E value = current.item;
				if (hasNext()) {
					if (current.next == null) {
						current.next = null;
					} else {
						current = current.next.next;
					}
				}
				return value;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Linked list iterator remove not supported");
			}

		};
		return oddIterator;
	}

	// testing functionality
	public static void main(String[] args) {
		DoubleComparator comparator = new DoubleComparator();
		SortedLinkedList<Double> dList = new SortedLinkedList<Double>(comparator);
		dList.add(3.02);
		dList.add(6.51);
		dList.add(1.67);
		Iterator<Double> iter = dList.iterator();
		int i = 0;
		while (iter.hasNext()) {
			System.out.println(dList.get(i));
			i++;
		}
	}

}
