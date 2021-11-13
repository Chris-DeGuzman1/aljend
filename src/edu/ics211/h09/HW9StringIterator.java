package edu.ics211.h09;

public class HW9StringIterator {
	// class variables
	private String tokens;
	private int count;

	public HW9StringIterator(String s) {
		// implementation of the constructor
		tokens = s;
		count = -1;
		
	}

	public boolean hasNext() {
		// implementation of hasNext
		if (count + 1 < tokens.length()) {
			return true;
		} else {
			return false;
		}
	}

	public char next() {
		// implementation of next -- may throw exception
		if (hasNext()) {
			count++;
			return tokens.charAt(count);
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	// back up by one position -- needed sometimes when parsing
	public void backUp() {
		// implementation of backUp -- throws exception if already at beginning
		if(count < -1){
			throw new IndexOutOfBoundsException();
		}
		count--;
	}
}