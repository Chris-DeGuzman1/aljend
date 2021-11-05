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
class WhileLoopTest {

	WhileLoop wl;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		wl = new WhileLoop("while (a > 3) { a--; }");
	}

	@Test
	void test() {
		assertTrue(wl.isCompound());
		assertEquals(wl.numberOfParts(), 1);
	}

}