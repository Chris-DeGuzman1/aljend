package edu.ics211.h07;

import java.util.EmptyStackException;

/**
 * @author Chris DeGuzman
 * @date October 22, 2021
 * @summary creates a stack using an array of a max length of 3.
 */
public class ArrayStack<E> {
	private int size;
	private int top;
	private int maxLength = 3;
	private E[] stackArr;

	@SuppressWarnings("unchecked")
	public ArrayStack() {
		stackArr = (E[]) new Object[maxLength];
		top = -1;
		size = 0;
	}

	public boolean empty() {
		if (stackArr[0] == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean full() {
		if (stackArr[2] == null) {
			return false;
		} else {
			return true;
		}
	}

	public void push(E value) {
		if (this.full()) {
			throw new StackOverflowError();
		} else {
			top++;
			size++;
			stackArr[top] = value;
		}
	}

	public E pop() {
		if (this.empty()) {
			throw new EmptyStackException();
		} else {
			E value = stackArr[top];
			stackArr[top] = null;
			top--;
			size--;
			return value;
		}
	}

	public int size() {
		return size;
	}

	public E peek() {
		return stackArr[top];
	}

}
