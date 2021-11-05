package edu.ics211.h02;

public class Conditional extends CompoundStatement {
	public Conditional(String in) throws InvalidStatementException {
		this.text = in;
		this.textTokens = lexicalTokens(this.text);
	}
	public Conditional() {
		
	}
	// iterates through textTokens array, looks for and counts all instances of "if(" and "else{"
	@Override
	public int numberOfParts() {
		int parts = 0;
		if (this.isCompound()) {
			for (int i = 0; i < this.textTokens.length; i++) {
				if (this.textTokens[i].equals("else") && this.textTokens[i+1].equals("{")) {
					parts++;
				}
				else if(this.textTokens[i].equals("if") && this.textTokens[i+1].equals("(")) {
					parts++;
				}
				
			}
		}
		return parts;
	}

}
