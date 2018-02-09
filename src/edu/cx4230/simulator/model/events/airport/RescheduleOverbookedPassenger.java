package edu.cx4230.simulator.model.events.airport;

import edu.cx4230.simulator.model.entity.airport.Flight;
import edu.cx4230.simulator.model.entity.airport.Passenger;
import edu.cx4230.simulator.model.models.AirportModel;
import edu.cx4230.simulator.util.Constants;
import edu.cx4230.simulator.util.Distributions;

public class RescheduleOverbookedPassenger extends AirportEvent {

    private Passenger passenger;

    public RescheduleOverbookedPassenger(Passenger passenger) {
        super(passenger.getArrivalTime());
        this.passenger = passenger;
    }

    @Override
    public int executeEvent(AirportModel model) {
        Flight nextFlight = model.getNextFlight(this.passenger.getFlightNumber(), this.passenger.getDepartureTime());
        this.passenger.addToCompensationAmount(Distributions.compensationDistribution());
        model.addOverbookedPassenger(this.passenger);
        if (nextFlight == null) {
            return Constants.EVENT_ERROR_CODE;
        }
        int numberOfBookedPassengersOnNextFlight = nextFlight.getSizeOfBoardingList();
        String newPassengerId = "FL" + this.passenger.getFlightNumber() + "-" +
                nextFlight.getDepartureTime() + "-" + (numberOfBookedPassengersOnNextFlight + 1);
        this.passenger.setDepartureTime(nextFlight.getDepartureTime());
        this.passenger.setId(newPassengerId);
        this.passenger.upgradeOverbookedToStandby();
        nextFlight.addToStandbyList(this.passenger);
        return this.getTimestamp();
    }
}
