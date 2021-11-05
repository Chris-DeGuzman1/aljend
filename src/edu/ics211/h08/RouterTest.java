package edu.ics211.h08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RouterTest {
	Router r;
	PacketSender s;

	@BeforeEach
	void setUp() throws Exception {
		s = new PacketSender();
		r = new Router(s);
	}

	void rtest() {
		int[][] counter = new int[10][1];
		int packets;
		for (int i = 1; i < 8; i++) {
			for (int j = 0; j < 10; j++) {
				r.acceptPacket(i, new Packet(0));
			}
		}
		r.advanceTime();
		counter[1][0] = 0;
		assertEquals(counter[1][0], s.q);
		r.advanceTime();
		counter[2][0] = 0;
		assertEquals(counter[2][0], s.q);
		r.advanceTime();
		counter[3][0] = 0;
		assertEquals(counter[3][0], s.q);
		r.advanceTime();
		counter[4][0] = 0;
		assertEquals(counter[4][0], s.q);
		r.advanceTime();
		counter[5][0] = 0;
		assertEquals(counter[5][0], s.q);
		r.advanceTime();
		counter[5][0] = 0;
		assertEquals(counter[5][0], s.q);
		r.advanceTime();
		counter[6][0] = 0;
		assertEquals(counter[6][0], s.q);
		r.advanceTime();
		counter[7][0] = 0;
		assertEquals(counter[7][0], s.q);
		r.advanceTime();
		counter[8][0] = 0;
		assertEquals(counter[8][0], s.q);
		r.advanceTime();
		counter[9][0] = 0;
		assertEquals(counter[9][0], s.q);
		
	}

	@Test
	void test() {
		rtest();
	}

}
