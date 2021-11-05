package edu.ics211.h02;

public class MethodCall extends BasicStatement {
	public MethodCall(String in) throws InvalidStatementException {
		super(in);
	}
	public MethodCall() {
		
	}

	public String getMethodName() {
		String methodName = "";
		if (Character.isJavaIdentifierPart(this.textTokens[0].charAt(0)) && this.textTokens[1].equals("(")) {
			methodName = this.textTokens[0];
		}
		return methodName;
	}

}
