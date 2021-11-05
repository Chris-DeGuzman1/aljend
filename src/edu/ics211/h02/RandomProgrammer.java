package edu.ics211.h02;

import java.util.Random;

public class RandomProgrammer implements Programmer {
	Random rand = new Random();
	int selector = rand.nextInt(4);
	int arrSelector = rand.nextInt(10);
	final static String[] variables = { "a", "b", "c", "i", "n", "x", "y", "z", "w", "count" };
	final static String[] methodNames = { "isCorrect", "loop", "makeBig", "makeSmall", "findThis", "makePretty",
			"makeAesthetic", "scramble", "makeBreakfast", "isHungry" };

	// constructor
	public RandomProgrammer(int maxDepth) {
		makeStatement(maxDepth);
	}

	// returns a random variable, number, or a methodCall
	private String makeExpression(int maxDepth) {
		int type = rand.nextInt(3);
		int randNum = rand.nextInt(100);
		if (maxDepth > 0) {
			switch (type) {
			case 0:
				return variables[arrSelector];
			case 1:
				return String.valueOf(randNum);
			case 2:
				makeMethodCall(maxDepth - 1).getText();
			default:
				return String.valueOf(randNum);
			}
		} else {
			switch (type) {
			case 0:
				return variables[arrSelector];
			case 1:
				return String.valueOf(randNum);
			case 2:
				makeMethodCall(maxDepth - 1).getText();
			default:
				return String.valueOf(randNum);
			}
		}
	}

	@Override
	public Assignment makeAssignment(int maxDepth) {
		String thisAssignment = "";
		if (maxDepth > 0) {
			thisAssignment += variables[arrSelector] + "=" + makeExpression(maxDepth - 1);
		} else {
			thisAssignment = variables[arrSelector] + "=" + makeExpression(0);
		}
		try {
			return new Assignment(thisAssignment);
		} catch (InvalidStatementException e) {
			e.printStackTrace();
			throw new RuntimeException("Expression is invalid");
		}
	}

	@Override
	public MethodCall makeMethodCall(int maxDepth) {
		String methodName = methodNames[arrSelector] + "("; // choose name
		try {
			if (maxDepth > 0) {
				switch (selector) {
				case 0:
					methodName += ")";
					return new MethodCall(methodName);
				case 1:
					methodName += makeExpression(maxDepth - 1) + ")";
					return new MethodCall(methodName);
				case 2:
					methodName += makeExpression(maxDepth - 1) + makeExpression(maxDepth - 1) + ")";
					return new MethodCall(methodName);
				case 3:
					methodName += makeExpression(maxDepth - 1) + makeExpression(maxDepth - 1)
							+ makeExpression(maxDepth - 1) + ")";
					return new MethodCall(methodName);
				default:
					return new MethodCall(methodName + ")");
				}
			} else {
				return new MethodCall(methodName + ")");
			}

		} catch (InvalidStatementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Expression is invalid");
		}

	}

	@Override
	public WhileLoop makeWhileLoop(int maxDepth) {
		// TODO Auto-generated method stub
		try {
			String whileLoop = "";
			if (maxDepth > 0) {
				whileLoop = "while(" + variables[arrSelector] + " > " + String.valueOf(rand.nextInt(10 - 1) + 1)
						+ ") { \n" + makeStatement(maxDepth - 1) + "\n}";
				return new WhileLoop(whileLoop);
			} else {
				return new WhileLoop("while(" + variables[arrSelector] + " > "
						+ String.valueOf(rand.nextInt(10 - 1) + 1) + makeStatement(0) + "\n}");
			}
		} catch (InvalidStatementException e) {
			e.printStackTrace();
			throw new RuntimeException("Expression is invalid");
		}
	}

	@Override
	public Conditional makeConditional(int maxDepth) {
		// TODO Auto-generated method stub
		try {
			int parts = rand.nextInt(3);
			String thisCondition = "if(" + variables[arrSelector] + " > " + makeExpression(maxDepth - 1) + ") {\n"
					+ makeStatement(maxDepth - 1) + "\n}";
			if (maxDepth > 0) {
				switch (parts) {
				case 0:
					return new Conditional(thisCondition);
				case 1:
					thisCondition += "else if(" + variables[arrSelector] + " > " + makeExpression(maxDepth - 1)
							+ ") {\n" + makeStatement(maxDepth - 1) + "\n}";
					return new Conditional(thisCondition);
				case 2:
					thisCondition += "else if(" + variables[arrSelector] + " > " + makeExpression(maxDepth - 1)
							+ ") {\n" + makeStatement(maxDepth - 1) + "\n}";
					thisCondition += "else if(" + variables[arrSelector] + " > " + makeExpression(maxDepth - 1)
							+ ") {\n" + makeStatement(maxDepth - 1) + "\n}";
					return new Conditional(thisCondition);
				default:
					return new Conditional(thisCondition);
				}

			} else { // if maxDepth is zero, will return a default conditional
				return new Conditional("if(" + variables[arrSelector] + " > " + makeExpression(maxDepth - 1) + ") {\n"
						+ makeStatement(0) + "\n}");
			}
		} catch (InvalidStatementException e) {
			e.printStackTrace();
			throw new RuntimeException("Expression is invalid");
		}
	}

	@Override
	public Statement makeStatement(int maxDepth) {
		// TODO Auto-generated method stub
		String thisStatement = "";
		try {
			if (maxDepth > 0) { // checks if maxDepth isn't zero
				switch (selector) {
				case 0:
					thisStatement += makeAssignment(maxDepth - 1).getText();
					return new Assignment(thisStatement);

				case 1:
					thisStatement += makeMethodCall(maxDepth - 1).getText();
					return new MethodCall(thisStatement);

				case 2:
					thisStatement += makeWhileLoop(maxDepth - 1).getText();
					return new WhileLoop(thisStatement);

				case 3:
					thisStatement += makeConditional(maxDepth - 1).getText();
					return new Conditional(thisStatement);
				default:
					thisStatement += makeAssignment(0).getText();
					return new Assignment(thisStatement);

				}
			} else {
				/*
				 * if makeStatement is called with a maxDepth of zero, the method should
				 * complete one last statement, passing off the 0 to the other methods which
				 * should return default statements of their respective types
				 */
				switch (selector) {
				case 0:
					thisStatement += makeAssignment(0).getText();
					return new Assignment(thisStatement);

				case 1:
					thisStatement += makeMethodCall(0).getText();
					return new MethodCall(thisStatement);

				case 2:
					thisStatement += makeWhileLoop(0).getText();
					return new WhileLoop(thisStatement);

				default: // added default because eclipse will force me to add a return null at the end
							// of the method
					thisStatement += makeAssignment(0).getText();
					return new Assignment(thisStatement);
				}
			}

		} catch (InvalidStatementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Expression is invalid");
		}

	}

}
