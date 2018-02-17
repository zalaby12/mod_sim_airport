import edu.cx4230.simulator.model.entity.airport.PassengerStatus;

/*
 * Passenger used for testing, using an ID to distinguish between unique
 * entities instead of flight number and departure time.
 */
public class TestPassenger implements Comparable<TestPassenger> {

    PassengerStatus status;
    int id = -1;

    public TestPassenger(PassengerStatus status, int id) {
        this.status = status;
        this.id = id;
    }

    @Override
    public int compareTo(TestPassenger o) {
        return o.status.asInt() - this.status.asInt();
    }
}
