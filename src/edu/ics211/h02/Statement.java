package edu.ics211.h02;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Statement {
	protected String text;
	protected String[] textTokens;

	public String getText() {
		return text;
	}

	/**
	 * @param: the string to analyze
	 * @param: the index to look into for //
	 * @return: whether this string begins with //
	 */
	private static boolean isCommentToEndOfLine(String in, int start) {
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
	 * @return: the index of the next \n if any, and otherwise the index of the last
	 *          character in the string
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
	private static boolean isCommentAcrossLines(String in, int start) {
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
	 * @return: the index of the next the comment close, if any, and otherwise the
	 *          index of the last character in the string prints a message if the
	 *          comment is not properly closed
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
	 * @return: an array with the tokens of the string: Java identifiers, and
	 *          individual characters that are neither whitespace nor comments
	 * @note: currently numbers are tokenized into their individual digits, a little
	 *        extra work (analogous to tokenizing identifiers) would let us tokenize
	 *        them correctly.
	 */
	public String[] lexicalTokens(String in) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			if (Character.isJavaIdentifierStart(c)) {
				StringBuilder Id = new StringBuilder();
				Id.append(c);
				i++;
				while ((i < in.length()) && Character.isJavaIdentifierPart(in.charAt(i))) { // loop until the end of the
																							// ID
					Id.append(in.charAt(i));
					i++;
				}
				i--; // because the last increment was one too many
				result.add(Id.toString());
			} else if (isCommentToEndOfLine(in, i)) {
				// found the start of a Java inline comment, skip to the end of the line
				i = indexOfEndOfLine(in, i);
			} else if (isCommentAcrossLines(in, i)) {
				i = indexOfEndOfComment(in, i);
			} else if (!Character.isWhitespace(c)) {
				char[] symbol = { c };
				result.add(new String(symbol));
			}
		}
		String[] typeMarker = new String[1];
		return result.toArray(typeMarker);
	}

	public abstract boolean isCompound();

	@Override
	public String toString() {
		return text;
	}

	
	public boolean equals(Statement obj) {
		if(obj == null) {
			return false;
		}
		
		if ((this.textTokens.length == obj.textTokens.length)) {
			for(int i=0; i<textTokens.length; i++) {
				if(!this.textTokens[i].equals(obj.textTokens[i])) {
					return false;
				}
			}
			
		}else {
			return false;
		}
		return this.equals(obj);
	}

}
