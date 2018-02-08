import edu.cx4230.simulator.model.events.Event;

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
