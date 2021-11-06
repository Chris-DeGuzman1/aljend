package edu.ics211.h09;

public class HW9StringIterator {
	// class variables
	private String[] tokens;
	private int count;

	public HW9StringIterator(String s) {
		// implementation of the constructor
		tokens = lexicalTokens(s);
		count = -1;
		for(String i: tokens){
			System.out.print(i);
		}
	}

	public boolean hasNext() {
		// implementation of hasNext
		if (count< tokens.length && tokens[count+1] != null) {
			return true;
		} else {
			return false;
		}
	}

	public String next() {
		// implementation of next -- may throw exception
		if (hasNext()) {
			count++;
			return tokens[count++];
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	// back up by one position -- needed sometimes when parsing
	public void backUp() {
		// implementation of backUp -- throws exception if already at beginning
		if(count == 0){
			throw new IndexOutOfBoundsException();
		}
		count--;
	}



	// copied and pasted lexical tokens methods, will use if required
	/**
	 * @param: the string to analyze
	 * @param: the index to look into for //
	 * @return: whether this string begins with //
	 */
	private static boolean isCommentToEndOfLine (String in, int start) {
		if (start + 1 < in.length()) {
			int c1 = in.charAt(start);
			int c2 = in.charAt(start + 1);
			return ((c1 == '/') && (c2 == '/'));
		}
		return false;
	}

	/**
	 * @param: the string to analyze
	 * @param: the first character position to look into for \n
	 * @return: the index of the next \n if any, and otherwise
	 *          the index of the last character in the string
	 */
	private static int indexOfEndOfLine(String in, int start) {
		for (int i = start; i < in.length(); i++) {
			if (in.charAt(i) == '\n') {
				return i;
			}
		}
		return in.length() - 1;
	}

	/**
	 * @param: the string to analyze
	 * @param: the index to look into for / *
	 * @return: whether this string begins with / *
	 */
	private static boolean isCommentAcrossLines (String in, int start) {
		if (start + 1 < in.length()) {
			int c1 = in.charAt(start);
			int c2 = in.charAt(start + 1);
			return ((c1 == '/') && (c2 == '*'));
		}
		return false;
	}

	/**
	 * @param: the string to analyze
	 * @param: the first character position to look into for the comment close
	 * @return: the index of the next the comment close, if any, and otherwise
	 *          the index of the last character in the string
	 * prints a message if the comment is not properly closed
	 */
	private static int indexOfEndOfComment(String in, int start) {
		for (int i = start; i + 1 < in.length(); i++) {
			if ((in.charAt(i) == '*') && (in.charAt(i + 1) == '/')) {
				return i + 1;
			}
		}
		System.out.println("error: comment reaches end of file");
		return in.length() - 1;
	}

	/**
	 * @param: the string to analyze
	 * @return: an array with the tokens of the string:
	 *          Java identifiers, and individual characters that
	 *          are neither whitespace nor comments
	 * @note: currently numbers are tokenized into their individual
	 *          digits, a little extra work (analogous to tokenizing
	 *          identifiers) would let us tokenize them correctly.
	 */
	public String[] lexicalTokens(String in) {
		java.util.ArrayList<String> result = new java.util.ArrayList<String>();
		for (int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			if (Character.isJavaIdentifierPart (c)) {
				StringBuilder Id = new StringBuilder();
				Id.append(c);
				i++;
				while ((i < in.length()) && Character.isJavaIdentifierPart(in.charAt(i))) {  // loop until the end of the ID
					Id.append(in.charAt(i));
					i++;
				}
				i--;  // because the last increment was one too many
				result.add(Id.toString());
			} else if (isCommentToEndOfLine(in, i)) {
				// found the start of a Java inline comment, skip to the end of the line
				i = indexOfEndOfLine(in, i);
			} else if (isCommentAcrossLines(in, i)) {
				i = indexOfEndOfComment(in, i);
			} else if (! Character.isWhitespace(c)) {
				char[] symbol = { c };
				result.add(new String(symbol));
			}
		}
		String[] typeMarker = new String[1];
		return result.toArray(typeMarker);
	}
}