package edu.ics211.h02;

public class BasicStatement extends Statement{ 
	public BasicStatement(String in) throws InvalidStatementException {
		this.text = in;
		this.textTokens = lexicalTokens(this.text);
	}
	public BasicStatement() {
	}
	// always returns false
	@Override
	public boolean isCompound() {
		return false;
	}

}
