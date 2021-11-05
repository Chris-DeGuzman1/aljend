/**
 * 
 */
package edu.ics211.h04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Chris DeGuzman
 *
 */
class SymbolTableTest {

	/**
	 * @throws java.lang.Exception
	 */
	Random rand;
	SymbolTable symbols;
	private String[] original = { "U", "H", "I", "L", "B", "O", "J", "A", "M", "G", "X", "R", "Y", "P", "E", "C", "V",
			"D", "S", "N", "Q", "T", "F", "Z", "W", "K" };
	private String expected = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
	private String expected2 = "A B C D E F G H I J K L M N O P Q S T U V W X Y Z";
	private String[] original2 = { "p", "o", "b", "o", "i", "p" };
	private String expected3 = "b i o p";

	@BeforeEach
	void setUp() throws Exception {
		rand = new Random();
		symbols = new SymbolTable();
	}

	private void SymbolTestAdd(String[] original, String expected) {
		symbols = new SymbolTable();
		for (int i = 0; i < original.length; i++) {
			symbols.add(original[i]);
		}
		
		String temp = symbols.toString();
		
		assert (symbols.size() == 26);
		assertEquals(temp, expected);
	}

	// tests to see if the program will detect if the array already has the same
	// value in it
	private void SymbolTestAdd2(String[] original, String expected) {
		symbols = new SymbolTable();
		for (int i = 0; i < original.length; i++) {
			symbols.add(original[i]);
		}
		String temp = symbols.toString();
		assertEquals (4, symbols.size());
		assertEquals(temp, expected);
	}

	// I know this is a very specific use case, but it demonstrates that the remove
	// method works and it doesn't break the sort method if you want to add more to
	// the SymbolTable object
	private void SymbolTestRemove(String[] original, String expected) {
		symbols = new SymbolTable();
		for (int i = 0; i < original.length; i++) {
			symbols.add(original[i]);
		}
		symbols.remove("R");
		// calls toString to resort
		String temp = symbols.toString();
		assert (symbols.size() == 25);
		assertEquals(expected, temp);
	}

	@Test
	void test() {
		SymbolTestAdd(original, expected);
		SymbolTestAdd2(original2, expected3);
		SymbolTestRemove(original, expected2);

	}

}
