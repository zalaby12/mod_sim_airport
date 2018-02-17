package edu.cx4230.simulator.model.events;

/*
 * Basic class implementation for an Event. requires a timestamp to instantiate
 * and will save each event class from defining how two events are comparable (by their
 * timestamp).
 */
public abstract class Event implements Comparable<Event> {

    int timestamp;

    public Event(int timestamp) { this.timestamp = timestamp; }

    public int compareTo(Event event) { return event.timestamp - this.timestamp; }

    public int getTimestamp() { return this.timestamp; }

}
