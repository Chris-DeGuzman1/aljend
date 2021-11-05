package edu.ics211.h08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

class QueueTest {
	private PacketQueue p1;
	private PacketQueue p2;
	private Random rand;

	@BeforeEach
	void setUp() throws Exception {
		p1 = new PacketQueue();
		p2 = new PacketQueue();
		rand = new Random();
	}

	void qtest1() {
		boolean t = false;
		try {
			p1.poll();
		} catch (IndexOutOfBoundsException e) {
			t = true;
		}
		assertTrue(t);
	}

	void qtest2() {
		boolean t = false;
		try {
			p2.offer(new Packet(1));
			p2.poll();
		} catch (IndexOutOfBoundsException e) {
			t = true;
		}
		assertFalse(t);
	}

	void qtest3() {
		boolean t = false;
		int r = rand.nextInt(7-0)+0;
		for (int i = 0; i < 10; i++) {
			p1.offer(new Packet(r));
		}
		try {
			p1.offer(new Packet(1));
		}catch(StackOverflowError e) {
			t = true;
		}
		assertTrue(t);
	}

	void qtest4() {
		boolean t = false;
		try {
			p1.poll();
			p1.poll();
		}catch(IndexOutOfBoundsException e) {
			t = true;
		}
		assertFalse(t);
	}

	@Test
	void test() {
		qtest1();
		qtest2();
		qtest3();
		qtest4();
	}
}
