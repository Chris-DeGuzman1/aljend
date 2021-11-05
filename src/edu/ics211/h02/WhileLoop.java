package edu.ics211.h02;

public class WhileLoop extends CompoundStatement{
	public WhileLoop(String in) throws InvalidStatementException {
		this.text = in;
		String[] tokenArr = lexicalTokens(this.text);
	}
	public WhileLoop() {
		
	}

	@Override
	public int numberOfParts() {
		// TODO Auto-generated method stub
		int parts = 0;
		if (this.isCompound()) {
			for (int i = 0; i < this.textTokens.length; i++) {
				if (this.textTokens[i].equals("while") && this.textTokens[i+1].equals("(")) {
					parts++;
				}
			}
		}
		return parts;
	}
}
