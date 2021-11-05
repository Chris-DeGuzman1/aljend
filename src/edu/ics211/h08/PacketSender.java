package edu.ics211.h08;

public class PacketSender implements PacketSenderInterface{
	int q;
	int pAdd;
	@Override
	public void send(int queue, Packet p) {
		q=queue;
		pAdd = p.getAddress();
		System.out.print(queue +" "+ p.getAddress());
	}

}
