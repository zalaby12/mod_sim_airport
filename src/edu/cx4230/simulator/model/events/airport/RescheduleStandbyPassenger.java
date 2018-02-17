package edu.cx4230.simulator.model.events.airport;

import edu.cx4230.simulator.model.entity.airport.Flight;
import edu.cx4230.simulator.model.entity.airport.Passenger;
import edu.cx4230.simulator.model.models.AirportModel;
import edu.cx4230.simulator.util.Constants;

/*
 * Rebooking standby passengers simply moves them to the standby list on the
 * next available flight. If that does not exist, we are near the end of the simulation
 * and nothing happens.
 */
public class RescheduleStandbyPassenger extends AirportEvent {

    private Passenger passenger;

    public RescheduleStandbyPassenger(Passenger passenger) {
        super(passenger.getArrivalTime());
        this.passenger = passenger;
    }

    @Override
    public int executeEvent(AirportModel model) {
        Flight nextFlight = model.getNextFlight(this.passenger.getFlightNumber(), this.passenger.getDepartureTime());
        if (nextFlight == null) {
            return Constants.EVENT_ERROR_CODE;
        }
        int numberOfBookedPassengersOnNextStandbyList = nextFlight.getStandbyList().size();
        String newPassengerId = "FL" + this.passenger.getFlightNumber() + "-" +
                nextFlight.getDepartureTime() + "-" + (numberOfBookedPassengersOnNextStandbyList + 1);
        this.passenger.setDepartureTime(nextFlight.getDepartureTime());
        this.passenger.setId(newPassengerId);
        nextFlight.addToStandbyList(this.passenger);
        return this.getTimestamp();
    }
}
