package edu.cx4230.simulator.model.events.airport;

import edu.cx4230.simulator.model.entity.airport.Flight;
import edu.cx4230.simulator.model.entity.airport.Passenger;
import edu.cx4230.simulator.model.models.AirportModel;
import edu.cx4230.simulator.util.Constants;
import edu.cx4230.simulator.util.Distributions;

/*
 * If a passenger arrives late, they pay for an additional ticket and are put
 * on the next available flight's boarding list. This assumes they are able to
 * make it immediately to the gate for the next flight. If there is no flight
 * available after this one, we are near the end of the simulation and nothing
 * happens
 */
public class RescheduleLateArrival extends AirportEvent {

    private Passenger passenger;

    public RescheduleLateArrival(Passenger passenger) {
        super(passenger.getArrivalTime());
        this.passenger = passenger;
    }

    @Override
    public int executeEvent(AirportModel model) {
        Flight nextFlight = model.getNextFlight(this.passenger.getFlightNumber(), this.passenger.getDepartureTime());
        if (nextFlight == null) {
            return Constants.EVENT_ERROR_CODE;
        }
        int numberOfBookedPassengersOnNextFlight = nextFlight.getSizeOfBoardingList();
        String newPassengerId = "FL" + this.passenger.getFlightNumber() + "-" +
                nextFlight.getDepartureTime() + "-" + (numberOfBookedPassengersOnNextFlight + 1);
        int ticketPrice = Distributions.ticketPriceDistribution();
        this.passenger.addToTicketPrice(ticketPrice);
        model.addToRevenue(ticketPrice);
        this.passenger.setDepartureTime(nextFlight.getDepartureTime());
        this.passenger.setId(newPassengerId);
        nextFlight.addToBoardingList(this.passenger);
        return this.getTimestamp();
    }
}
