package edu.ics211.h09;

public class Parser {
    public Parser() {

    }

    public static String parseFactor(HW9StringIterator in) {
        StringBuilder result = new StringBuilder();
        result.append(in.next());
        return result.toString();
    }

    public static String parseExp(HW9StringIterator in) {
        StringBuilder result = new StringBuilder();
        while(in.hasNext()) {
            if (in.next().equals("(")) {
                result.append(parseParen(in));
            } else {
                in.backUp();
                result.append(parseTerm(in));
            }
        }
        return result.toString();
    }

    public static String parseParen(HW9StringIterator in) {
        StringBuilder result = new StringBuilder();
        result.append(parseTerm(in));
        return result.toString();
    }

    public static String parseTerm(HW9StringIterator si) {
        StringBuilder result = new StringBuilder();
        if (!si.hasNext()) { /* error, print and throw an exception */ }
        // the first thing we expect is an operand
        // we immediately add it to the output postfix expression
        result.append(parseFactor(si));
        boolean done = false;
        do {
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
                default:  // error, print and throw an exception
            }
            if (!done) {
                // now we read the second operand and add it to the result
                result.append(" ");
                result.append(parseFactor(si));
                result.append(" ");
                // now add the operator
                result.append(operator);
            }
        } while (!done);
        return result.toString();
    }

    // main method for testing
    public static void main(String[] args) {
        HW9StringIterator test = new HW9StringIterator("2+3*4");
        System.out.println("\n" + parseTerm(test));
    }

}
