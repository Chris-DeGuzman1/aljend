package edu.ics211.h09;

public class StringParser {
	private static int getOrder(String in) {
		switch (in) {
		case "*":
			return 1;
		case "/":
			return 1;
		case "%":
			return 1;
		case "+":
			return 2;
		case "-":
			return 2;
		default:
			return 0;
		}
	}

	public static String parseExp(HW9StringIterator si) {
		StringBuilder result = new StringBuilder();
		String operators = null;
		while (si.hasNext()) {
			result.append(parseTerm(si) + " ");
			if (si.hasNext()) {
				if (getOrder(Character.toString(si.next())) == 0) {
					result.append(operators);
					return result.toString();
				}
				si.backUp();
				if (operators == null) {
					operators = Character.toString(si.next());
				}else if (operators != null && getOrder(operators) == getOrder(Character.toString(si.next()))) {
					result.append(operators + " ");
					si.backUp();
					operators = Character.toString(si.next());
				}
			}
		}
		if (operators != null) {
			result.append(operators);
		}
		return result.toString();
	}

	public static String parseTerm(HW9StringIterator si) {
		StringBuilder result = new StringBuilder();
		if (!si.hasNext()) {
			/* error, print and throw an exception */
			return result.toString();
		}
		// the first thing we expect is an operand
		// we immediately add it to the output postfix expression
		result.append(parseFactor(si));
		boolean done = false;
		while (!done) {
			// for a legal expression, after the operand we may see an operator,
			// a closing parenthesis, or the end of the string
			if (!si.hasNext()) {
				break; // end of the string, we are done, exit the loop
			}
			char operator = si.next();
			switch (operator) {
			case '*':
				break;
			case '/':
				break;
			case '%':
				break;
			// if we find a token that is not part of the factor,
			// we back it up so that token can be processed by the method
			// that called us
			case '+':
				si.backUp();
				done = true;
				break;
			case '-':
				si.backUp();
				done = true;
				break;
			case ')':
				si.backUp();
				done = true;
				break;
			default: // error, print and throw an exception
			}
			if (!done) {
				// now we read the second operand and add it to the result
				result.append(" ");
				result.append(parseFactor(si));
				result.append(" ");
				// now add the operator
				result.append(operator);
			}
		}
		return result.toString();
	}

	private static String parseFactor(HW9StringIterator si) {
		if (si.hasNext() && si.next() == '(') {
			return new StringBuilder(parseParen(si)).toString();
		}
		si.backUp();
		if (si.hasNext() && si.next() != '(') {
			si.backUp();
			return Character.toString(si.next());
		} else {
			si.backUp();
			return "";
		}

	}

	private static String parseParen(HW9StringIterator si) {
		return new StringBuilder(parseExp(si)).toString();
	}
}
