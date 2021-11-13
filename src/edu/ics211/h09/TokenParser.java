package edu.ics211.h09;

public class TokenParser {

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

	public static String parseExp(HW9StringTokenizer si) {
		StringBuilder result = new StringBuilder();
		String operators = null;
		while (si.hasNext()) {
			result.append(parseTerm(si) + " ");
			// assuming parseTerm() does its job correctly the next token should be an
			// operator
			if (si.hasNext()) {
				// if the next token is a ')' then getOrder() should default to 0
				if (getOrder(si.next()) == 0) {
					result.append(operators);
					return result.toString();
				}
				si.backUp();
				if (operators == null) {
					operators = si.next();
				} else if (operators != null && getOrder(operators) == getOrder(si.next())) {
					result.append(operators + " ");
					// added backup in order to record the next operator
					si.backUp();
					operators = si.next();
				}
			}
		}
		// once the program reaches the end of the equation, there should be one
		// operator remaining
		if (operators != null) {
			result.append(operators);
		}
		return result.toString();
	}

	public static String parseTerm(HW9StringTokenizer si) {
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
			String operator = si.next();
			switch (operator) {
			case "*":
				break;
			case "/":
				break;
			case "%":
				break;
			// if we find a token that is not part of the factor,
			// we back it up so that token can be processed by the method
			// that called us
			case "+":
				si.backUp();
				done = true;
				break;
			case "-":
				si.backUp();
				done = true;
				break;
			case ")":
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

	private static String parseFactor(HW9StringTokenizer si) {
		if (si.hasNext() && si.next().equals("(")) {
			return new StringBuilder(parseParen(si)).toString();
		}
		si.backUp();
		if (si.hasNext() && !si.next().equals("(")) {
			si.backUp();
			return si.next();
		}
		return "";

	}

	private static String parseParen(HW9StringTokenizer si) {
		return new StringBuilder(parseExp(si)).toString();
	}
	public static void main(String[] args) {
		HW9StringTokenizer test = new HW9StringTokenizer("2+4/5*(5-3)*5-4");
		System.out.println(parseExp(test));
	}
}
