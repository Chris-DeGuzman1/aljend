package edu.ics211.h08;

public class Packet {
    private int address;

    public Packet(int destination) {
        address = destination;
    }

    public int getAddress() {
        return address;
    }

    public String toString() {
        return "packet with destination " + address;
    }
}
