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
class BasicStatementTest {
	BasicStatement basic;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		basic = new BasicStatement("a = 3");
	}

	@Test
	void test() {
		assertFalse(basic.isCompound());
	}

}