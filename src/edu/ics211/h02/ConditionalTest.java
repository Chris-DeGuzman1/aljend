/**
 * 
 */
package edu.ics211.h02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author esb
 *
 */
class ConditionalTest {

	Conditional ifOnly;
	Conditional ifElse;
	Conditional ifElseIf;
	Conditional ifElseIfElse;
	
	final static String test1 = "if (a > 3) {\n"
			+ "    lifeIsGood();\n"
			+ "}";
	final static String test2 = "if (a == null) {\n"
			+ "    throw new Exception();\n"
			+ "} else {\n"
			+ "    return a;\n"
			+ "}";
	final static String test3 = "if (a == null) {\n"
			+ "    throw new Exception();\n"
			+ "} else if (a.equals(a)) {\n"
			+ "    return a;\n"
			+ "}";
	final static String test4 = "if (a == null) {\n"
			+ "    throw new Exception();\n"
			+ "} else if (a.equals(a)) {\n"
			+ "    return a;\n"
			+ "} else {\n"
			+ "    return null;\n"
			+ "}";
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		ifOnly = new Conditional(test1);
		ifElse = new Conditional(test2);
		ifElseIf = new Conditional(test3);
		ifElseIfElse = new Conditional(test4);
	}

	@Test
	void test() {
		assertTrue(ifOnly.isCompound());
		assertTrue(ifElse.isCompound());
		assertTrue(ifElseIf.isCompound());
		assertTrue(ifElseIfElse.isCompound());
		assertEquals(ifOnly.numberOfParts(), 1);
		assertEquals(ifElse.numberOfParts(), 2);
		assertEquals(ifElseIf.numberOfParts(), 2);
		assertEquals(ifElseIfElse.numberOfParts(), 3);
	}

}