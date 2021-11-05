package edu.ics211.h07;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Chris DeGuzman
 * @date October 22, 2021
 * @summary using the linkedNode class that Edo provided, this program implements a
 *       linked list as a stack and uses it for a calculator. Code for the
 *       calculator is copied from my Calculator.java class and is tweaked to
 *       use LinkedStackCalculator in place of stack.
 */

public class LinkedStack<E> {

	private LinkedNode<E> head;
	private LinkedNode<E> tail;
	private int size;

	public LinkedStack() {
		head = null;
		tail = null;
		size = 0;
	}

	public void push(E value) {
		LinkedNode<E> newNode = new LinkedNode<E>(value);
		newNode.item = value;
		size++;
		if (tail == null) {
			head = newNode;
			tail = head;
		} else {
			LinkedNode<E> temp = head;
			newNode.next = temp;
			head = newNode;
		}
	}

	public E pop() {
		if (this.empty()) {
			throw new EmptyStackException();
		} else {
			LinkedNode<E> temp = head;
			E value = temp.item;
			head = temp.next;
			size--;
			return value;
		}
	}

	public E peek() {
		return head.item;
	}

	public boolean empty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		return size;
	}

	private static class LinkedNode<E> {
		private E item;
		private LinkedNode<E> next;

		/**
		 * constructor to build a node with no successor
		 * 
		 * @param the value to be stored by this node
		 */
		private LinkedNode(E value) {
			item = value;
			next = null;
		}

		/**
		 * constructor to build a node with specified (maybe null) successor
		 * 
		 * @param the value to be stored by this node
		 * @param the next field for this node
		 */
		private LinkedNode(E value, LinkedNode<E> reference) {
			item = value;
			next = reference;
		}
	}
}
