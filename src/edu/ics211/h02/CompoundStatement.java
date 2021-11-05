package edu.ics211.h02;

public abstract class CompoundStatement extends Statement{
	// always returns true
	@Override
	public boolean isCompound() {
		return true;
	}
	
	public abstract int numberOfParts();

}
