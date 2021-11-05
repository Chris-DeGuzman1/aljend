package edu.ics211.h08;

import java.util.ArrayList;
import java.util.List;

public class Router implements RouterInterface {
	private PacketQueue[] qArr;
	private ArrayList<Packet> dropped = new ArrayList<Packet>();
	private PacketSender sender;

	public Router(PacketSender s) {
		qArr = new PacketQueue[8];
		qArr[0] = new PacketQueue();
		qArr[1] = new PacketQueue();
		qArr[2] = new PacketQueue();
		qArr[3] = new PacketQueue();
		qArr[4] = new PacketQueue();
		qArr[5] = new PacketQueue();
		qArr[6] = new PacketQueue();
		qArr[7] = new PacketQueue();
		sender = s;
	}

	@Override
	public void advanceTime() {
		for (PacketQueue q : qArr) {
			int count = 0;
			if (q.size() != 0) {
				sender.send(count, q.poll());
			}
			count++;
		}
	}

	@Override
	public boolean acceptPacket(Packet p) {
		if (qArr[p.getAddress()].offer(p)) {
			return true;
		} else {
			dropped.add(p);
			throw new StackOverflowError();
		}
	}
	// overloaded method to allow the queues to be filled with packets that send to queue 0
	public boolean acceptPacket(int queue, Packet p) {
		if(qArr[queue].offer(p)) {
			return true;
		}else {
			dropped.add(p);
			return false;
		}
	}

	@Override
	public List<Packet> getDroppedPackets() {
		List<Packet> temp = dropped;
		dropped.clear();
		return temp;
	}
}
