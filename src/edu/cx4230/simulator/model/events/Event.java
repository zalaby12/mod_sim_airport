package edu.cx4230.simulator.model.events;

public abstract class Event implements Comparable<Event> {

    int timestamp;

    public Event(int timestamp) { this.timestamp = timestamp; }

    public int compareTo(Event event) { return event.timestamp - this.timestamp; }

    public int getTimestamp() { return this.timestamp; }

}
