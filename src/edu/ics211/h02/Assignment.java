package edu.ics211.h02;

public class Assignment extends BasicStatement {
	public Assignment(String in) throws InvalidStatementException {
		super(in);
	}
	public Assignment() {
		
	}
	
	// checks the first character of the first token in the token array is a java identifier and the second token is an equals, method will return the first token
	public String getVariable() {
		String var = "";
		if (Character.isJavaIdentifierPart(this.textTokens[0].charAt(0)) && this.textTokens[1].equals("=")) {
			var = this.textTokens[0];
		}
		return var;
	}

}
