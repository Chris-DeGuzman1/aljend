package edu.ics211.h01;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Chris DeGuzman
 *
 */
public class Lex implements Lexer {
	private ArrayList<String> result;
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
		result = new ArrayList<String>();
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


  @Override
  public String[] lexicalTokens(InputStream in) {
    // basically a copy and paste of the other lexicalTokens method but with a few
    // slight changes
    Scanner inScan = new Scanner(in);
    ArrayList<String> temp = new ArrayList<String>();
    String token = "";

    // the scanner reads the file line by line
    while (inScan.hasNextLine()) {
      String line = inScan.nextLine();
      for (int i = 0; i < line.length(); i++) {
        // it's much simpler to skip the line than to look for the end of the block
        // comment
        if ((line.charAt(i) == '*') || (line.charAt(i) == '/')) {
          break;
        }
        // the rest is basically the same
        if (Character.isJavaIdentifierStart(line.charAt(i))
            || Character.isJavaIdentifierPart(line.charAt(i))) {
          token += line.charAt(i);
        } else {
          temp.add(token);
          token = "";

        }
        if ((line.charAt(i) == '(') || (line.charAt(i) == ')') || (line.charAt(i) == '[')
            || (line.charAt(i) == ']') || (line.charAt(i) == '{') || (line.charAt(i) == '}')
            || (line.charAt(i) == ';') || (line.charAt(i) == '.') || (line.charAt(i) == ',')) {
          temp.add(Character.toString(line.charAt(i)));
        }

      }

    }
    inScan.close();
    temp.removeAll(Arrays.asList(""));

    String[] typeMarker = new String[temp.size()];
    return temp.toArray(typeMarker);
  }

}
