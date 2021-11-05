package edu.ics211.h08;

public interface PacketSenderInterface {
    // queue number is 0..7
    void send(int queue, Packet p);
}