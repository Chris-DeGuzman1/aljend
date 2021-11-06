package edu.ics211.h09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParserTest {
	private String eq1;
	private String eq2;
	private String eq3;
	private HW9StringIterator si;
	private HW9StringTokenizer st;
	
	@BeforeEach
	void setUp() throws Exception {
		eq1 = "2+3%4";
		eq2 = "8-3-2-1";
		eq3 = "2+4/5*(5-3)*5-4";
	}
	private void test1(String in) {
		si = new HW9StringIterator(in);
		String parsed = StringParser.parseExp(si);
		assertEquals("2 3 4 % +", parsed);
		st = new HW9StringTokenizer(in);
		parsed = TokenParser.parseExp(st);
		assertEquals("2 3 4 % +", parsed);
	}
	private void test2(String in) {
		si = new HW9StringIterator(in);
		String parsed = StringParser.parseExp(si);
		assertEquals("8 3 - 2 - 1 -", parsed);
		st = new HW9StringTokenizer(in);
		parsed = TokenParser.parseExp(st);
		assertEquals("8 3 - 2 - 1 -", parsed);
	}
	private void test3(String in) {
		si = new HW9StringIterator(in);
		String parsed = StringParser.parseExp(si);
		assertEquals("2 4 5 / 5 3 - * 5 * + 4 -", parsed);
		st = new HW9StringTokenizer(in);
		parsed = TokenParser.parseExp(st);
		assertEquals("2 4 5 / 5 3 - * 5 * + 4 -", parsed);
	}

	@Test
	void test() {
		test1(eq1);
		test2(eq2);
		test3(eq3);
	}

}
