package edu.cx4230.simulator.model.events.airport;

import edu.cx4230.simulator.model.entity.airport.Flight;
import edu.cx4230.simulator.model.entity.airport.PassengerStatus;
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
        Flight flight = model.getFlight(this.passenger.getFlightNumber(), this.passenger.getDepartureTime());
        if (!flight.boardingDoorIsClosed()) {
            if (this.passenger.getPassengerStatus() == PassengerStatus.REV) {
                flight.addToBoardingList(this.passenger);
            } else {
                flight.addToStandbyList(this.passenger);
            }
        } else {
            if (this.passenger.getPassengerStatus() == PassengerStatus.REV) {
                model.rescheduleLateArrival(this.passenger);
            }
        }
        return this.getTimestamp();
    }

}
