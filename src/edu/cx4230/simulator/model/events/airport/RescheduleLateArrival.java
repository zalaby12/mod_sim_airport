package edu.cx4230.simulator.model.events.airport;

import edu.cx4230.simulator.model.entity.airport.Flight;
import edu.cx4230.simulator.model.entity.airport.Passenger;
import edu.cx4230.simulator.model.models.AirportModel;
import edu.cx4230.simulator.util.Distributions;

public class RescheduleLateArrival extends AirportEvent {

    private Passenger passenger;

    public RescheduleLateArrival(Passenger passenger) {
        super(passenger.getArrivalTime());
        this.passenger = passenger;
    }

    @Override
    public int executeEvent(AirportModel model) {
        Flight nextFlight = model.getNextFlight(this.passenger.getFlightNumber(), this.passenger.getDepartureTime());
        int numberOfBookedPassengersOnNextFlight = nextFlight.getSizeOfBoardingList();
        String newPassengerId = "FL" + this.passenger.getFlightNumber() + "-" +
                nextFlight.getDepartureTime() + "-" + (numberOfBookedPassengersOnNextFlight + 1);
        this.passenger.setDepartureTime(nextFlight.getDepartureTime());
        this.passenger.addToTicketPrice(Distributions.ticketPriceDistribution());
        this.passenger.setId(newPassengerId);
        return this.getTimestamp();
    }
}
