package edu.ics211.h08;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Queue;

public class PacketQueue extends AbstractQueue<Packet> implements Queue<Packet> {
	private Packet[] pArr;
	private int size;

	public PacketQueue() {
		pArr = new Packet[10];
		size = 0;
	}

	@Override
	public Iterator<Packet> iterator() {
		return new Iterator<Packet>() {
			int count = 0;

			@Override
			public boolean hasNext() {
				if (pArr[count] != null) {
					count++;
					return true;
				} else {
					return false;
				}
			}

			@Override
			public Packet next() {
				if (hasNext()) {
					return pArr[count];
				} else {
					return null;
				}
			}
		};
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean offer(Packet packet) {
		if (size == 10) {
			throw new StackOverflowError();
		} else {
			for (int count = 0; count < 10; count++) {
				if (pArr[count] == null) {
					pArr[count] = packet;
				}
			}
			size++;
			return true;
		}
	}

	@Override
	public Packet poll() {
		if (pArr[0] == null) {
			throw new IndexOutOfBoundsException();
		} else {
			Packet temp = pArr[0];
			pArr[0] = null;
			for (int count = 0; count < 9; count++) {
				pArr[count] = pArr[count + 1];
			}
			size--;
			return temp;
		}
	}

	@Override
	public Packet peek() {
		return pArr[0];
	}
}
