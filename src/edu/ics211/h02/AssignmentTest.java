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
class AssignmentTest {
	Assignment assignment;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		assignment = new Assignment("x = 99");
	}

	@Test
	void test() {
		assertFalse(assignment.isCompound());
		assertEquals(assignment.getVariable(), "x");
	}

}