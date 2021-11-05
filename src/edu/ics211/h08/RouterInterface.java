package edu.ics211.h08;

public interface RouterInterface {

    // constructor (constructors cannot be part of the interface)
    // sender.send is called whenever a packet is sent from a queue
    // public Router(PacketSenderInterface sender);

    // advance time by one time interval, removing packets from each of the
    // queues that are not empty and sending each by calling sender.send
    void advanceTime();

    // return true if the packet was queued, and false if it was dropped
    boolean acceptPacket(Packet p);

    // return a list of the packets that have been dropped since the last call
    // to this method, and clear the internal list of dropped packets
    java.util.List getDroppedPackets();
}