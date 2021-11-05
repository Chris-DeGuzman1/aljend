package edu.ics211.h07;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author Chris DeGuzman
 * @date October 22, 2021
 * @summary Calculator class
 *
 */
public class Calculator {
	// enumeration was the simplest suggestion that eclipse gave me for defining the
	// Operators for the stack
	private enum Operator {
		OPEN_PARENTHESIS, CLOSE_PARENTHASIS, EXPONENT, MULTIPLY, DIVIDE, MODULO, ADD, SUBTRACT, BLANK
	};

	/**
	 * @param: equation to evaluate
	 * @return: an Integer after the equation has been mathematically evaluated
	 * 
	 */
	public Integer calculate(String in) {
		Stack<Integer> operandStack = new Stack<Integer>();
		Stack<Operator> operatorStack = new Stack<Operator>();
		String[] numbers = lexicalTokens(in);
		for (int i = 0; i < numbers.length; i++) {
			if (isNumber(numbers[i])) {
				operandStack.push(Integer.valueOf(numbers[i]));
			} else {
				if (operatorStack.empty()) {
					operatorStack.push(getOperator(numbers[i]));
				} else if (operatorOrder(operatorStack.peek()) <= operatorOrder(getOperator(numbers[i]))) {
					int num2 = operandStack.pop();
					int num1 = operandStack.pop();
					Operator operate = operatorStack.pop();
					operandStack.push(evaluate(operate, num1, num2));
					// in the event that after evaluation of the operator that sets precedence the
					// previous operator is of equal precedence, the program will evaluate that as
					// well
					if (operandStack.size() >= 2 && operatorStack.size() >= 1) {
						if (operatorOrder(operatorStack.peek()) <= operatorOrder(getOperator(numbers[i]))) {
							num2 = operandStack.pop();
							num1 = operandStack.pop();
							operate = operatorStack.pop();
							operandStack.push(evaluate(operate, num1, num2));
						}
					}
					operatorStack.push(getOperator(numbers[i]));
				} else {
					operatorStack.push(getOperator(numbers[i]));
				}
			}
		}
		while (operandStack.size() >= 2 && operatorStack.size() >= 1) {
			Integer num2 = operandStack.pop();
			Integer num1 = operandStack.pop();
			Operator operate = operatorStack.pop();
			operandStack.push(evaluate(operate, num1, num2));
		}
		return operandStack.pop();
	}

	/**
	 * @param Operator used
	 * @param first    integer to be evaluated
	 * @param second   integer to be evaluated
	 * @return returns the result
	 */
	private int evaluate(Operator in, Integer num1, Integer num2) {
		Integer result;
		switch (in) {
		case EXPONENT:
			result = num1;
			for (int i = 1; i < num2; i++) {
				result *= num1;
			}
			return result;
		case MULTIPLY:
			return result = num1 * num2;
		case DIVIDE:
			return result = num1 / num2;
		case MODULO:
			return result = num1 % num2;
		case ADD:
			return result = num1 + num2;
		case SUBTRACT:
			return result = num1 - num2;
		default:
			return 0;
		}
	}

	/**
	 * 
	 * @param Takes in non-number, non-alphabetical string
	 * @return Operator type. When using pop(), the enum values are returned and
	 *         mathematical evaluation is handled by the method:
	 *         evaluate(Operator,Integer, Integer)
	 */
	private Operator getOperator(String in) {
		switch (in) {
		case "(":
			return Operator.OPEN_PARENTHESIS;
		case ")":
			return Operator.CLOSE_PARENTHASIS;
		case "^":
			return Operator.EXPONENT;
		case "*":
			return Operator.MULTIPLY;
		case "/":
			return Operator.DIVIDE;
		case "%":
			return Operator.MODULO;
		case "+":
			return Operator.ADD;
		case "-":
			return Operator.SUBTRACT;
		// default is there because even though it's never used, eclipse won't run the
		// program if it's not there
		default:
			return Operator.BLANK;
		}
	}

	/**
	 * 
	 * @param Operator
	 * @return Integer denoting mathematical precedence according to PEMDAS
	 */
	private int operatorOrder(Operator in) {
		switch (in) {
		case EXPONENT:
			return 1;
		case MULTIPLY:
			return 2;
		case DIVIDE:
			return 2;
		case MODULO:
			return 2;
		case ADD:
			return 3;
		case SUBTRACT:
			return 3;
		case BLANK:
			return 0;
		default:
			return 0;
		}
	}

	/**
	 * @param String to be analyzed
	 * @return False if NumberFormatException is thrown or if input is null. Returns
	 *         true if Integer i is successfully constructed
	 */
	private boolean isNumber(String in) {
		if (in == null) {
			return false;
		} else {
			try {
				Integer i = Integer.valueOf(in);
			} catch (NumberFormatException e) {
				return false;
			}
			return true;
		}

	}

	private ArrayList<String> result;

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
		result = new ArrayList<String>();
		for (int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			if (Character.isJavaIdentifierPart(c)) {
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

	// main method for testing
	public static void main(String[] args) {
		Scanner inScan = new Scanner(System.in);
		String in = inScan.nextLine();
		Calculator calc = new Calculator();
		System.out.println(calc.calculate(in));
	}
}
