package edu.ics211.h02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MethodCallTest {
	
	MethodCall mc;
	
	@BeforeEach
	void setUp() throws Exception {
		mc = new MethodCall("f(n)");
	}

	@Test
	void test() {
		assertFalse(mc.isCompound());
		assertEquals(mc.getMethodName(), "f");
	}

}