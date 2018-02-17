import edu.cx4230.simulator.model.events.Event;

/*
 * A lightweight class to test the extension of an Event and its effects on
 * insertion into the FEL
 */
public class TestEvent extends Event implements Comparable<Event>{

    public int id = -1;

    TestEvent(int timestamp) { super(timestamp); }

    TestEvent(int timestamp, int id) {
        this(timestamp);
        this.id = id;
    }

    @Override
    public String toString() {
        return getTimestamp() + "";
    }

}
