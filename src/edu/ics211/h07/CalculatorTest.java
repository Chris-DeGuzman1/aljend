package edu.ics211.h07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {
	Calculator calc;
	String equation1;
	String equation2;
	String equation3;
	String equation4;
	@BeforeEach
	void setUp() throws Exception {
		calc = new Calculator();
		equation1 = "2+3*4";
		equation2 = "2 + 3 ^ 4";
		equation3 = "8 - 3 - 2 - 1";
		equation4 = "99 / 7 % 5";
	}
	public void test1(String in) {
		int calculated = calc.calculate(in);
		assertEquals(14,calculated);
	}
	public void test2(String in) {
		int calculated = calc.calculate(in);
		assertEquals(83,calculated);
	}
	public void test3(String in) {
		int calculated = calc.calculate(in);
		assertEquals(2,calculated);
	}
	public void test4(String in) {
		int calculated = calc.calculate(in);
		assertEquals(4,calculated);
	}


	@Test
	void testCalculate() {
		test1(equation1);
		test2(equation2);
		test3(equation3);
		test4(equation4);
	}

}
