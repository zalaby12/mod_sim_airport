package edu.cx4230.simulator.model.events.airport;

import edu.cx4230.simulator.model.entity.airport.Flight;
import edu.cx4230.simulator.model.entity.airport.PassengerStatus;
import edu.cx4230.simulator.model.models.AirportModel;
import edu.cx4230.simulator.model.entity.airport.Passenger;

/*
 * Simulates the arrival of a passenger. The semantics of this event are pretty
 * well understood. If they're on-time they get put in either the standby list
 * or boarding list (if they're a rev passenger). If they're late, they get
 * rescheduled for another flight.
 */
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
