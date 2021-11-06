package edu.ics211.h09;

public class HW9StringIterator {
	// class variables
	String in;
	int count;
	
	public HW9StringIterator(String s) {
		// implementation of the constructor
		this.in = s;
		count = 0;
	}

	public boolean hasNext() {
		// implementation of hasNext
		if(in.substring(count) == null){
			return false;
		}else{
			return true;
		}
	}

	public char next() {
		// implementation of next -- may throw exception
		if(hasNext()){
			char i = in.charAt(count);
			count++;
			return i;
		}else{
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	// back up by one position -- needed sometimes when parsing
	public void backUp() {
		// implementation of backUp -- throws exception if already at beginning
	}

	public static String parseFactor(HW9StringIterator in){
		return null;
	}

	public static String parseExp(HW9StringIterator in){
		return null;
	}
	public static String ParseParen(HW9StringIterator in){
		return null;
	}
	public static String parseTerm(HW9StringIterator si) {
		StringBuilder result = new StringBuilder();
		if (! si.hasNext()) { /* error, print and throw an exception */ }
		// the first thing we expect is an operand
		// we immediately add it to the output postfix expression
		result.append(parseFactor(si));
		boolean done = false;
		do {
			// for a legal expression, after the operand we may see an operator,
			// a closing parenthesis, or the end of the string
			if (! si.hasNext()) {
				break; // end of the string, we are done, exit the loop
			}
			char operator = si.next();
			switch (operator) {
				case '*': break;
				case '/': break;
				case '%': break;
				// if we find a token that is not part of the factor,
				// we back it up so that token can be processed by the method
				// that called us
				case '+': si.backUp(); done = true; break;
				case '-': si.backUp(); done = true; break;
				case ')': si.backUp(); done = true;  break;
				default:  // error, print and throw an exception
			}
			if (! done) {
				// now we read the second operand and add it to the result
				result.append(" ");
				result.append(parseFactor(si));
				result.append(" ");
				// now add the operator
				result.append(operator);
			}
		} while (! done);
		return String.valueOf(result);
	}

	// lexical tokens methods copied. Will use if needed.
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
			if (Character.isJavaIdentifierStart (c)) {
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
