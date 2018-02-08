import edu.cx4230.simulator.model.entity.airport.PassengerStatus;

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
