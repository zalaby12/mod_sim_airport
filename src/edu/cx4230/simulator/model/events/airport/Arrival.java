package edu.cx4230.simulator.model.events.airport;

import edu.cx4230.simulator.model.models.AirportModel;
import edu.cx4230.simulator.model.entity.airport.Passenger;

public class Arrival extends AirportEvent {

    private Passenger passenger;

    public Arrival(Passenger passenger) {
        super(passenger.getArrivalTime());
        this.passenger = passenger;
    }

    @Override
    public int executeEvent(AirportModel model) {
        return 0;
    }

}
