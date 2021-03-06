package edu.cx4230.simulator.model.events.airport;

import edu.cx4230.simulator.model.entity.airport.Flight;
import edu.cx4230.simulator.model.entity.airport.Passenger;
import edu.cx4230.simulator.model.models.AirportModel;
import edu.cx4230.simulator.util.Constants;

/*
 * Simulates a plane departing. This event also asks the model to reschedule passengers
 * if needed. Semantically, if the flight is overbooked, all overbooked passengers are
 * placed on another flight. if not, then passengers from the standby list are moved
 * onto the flight. In either case, the remaining standby passengers are moved to the
 * next flight.
 */
public class BoardingDoorClose extends AirportEvent{

    private Flight flight;

    public BoardingDoorClose(Flight flight) {
        super(flight.getDepartureTime() - Constants.BOARDING_DOOR_CLOSE_WINDOW);
        this.flight = flight;
    }

    @Override
    public int executeEvent(AirportModel model) {
        this.flight.closeBoardingDoor();
        if (this.flight.isOverbooked()) {
            int numberOverbooked = this.flight.getSizeOfBoardingList() - this.flight.getCapacity();
            for (int i = 0; i < numberOverbooked; i++) {
                Passenger overbookedPassenger = this.flight.getBoardingList().removeRandom();
                model.rescheduleOverbookedPassenger(overbookedPassenger);
            }
            for (Passenger passenger : this.flight.getStandbyList()) {
                model.rescheduleStandbyPassenger(passenger);
            }
        } else {
            while (this.flight.getBoardingList().size() <= this.flight.getCapacity() && flight.getStandbyList().peek() != null) {
                int numberOfBookedPassengersOnBoardingList = flight.getBoardingList().size();
                Passenger nextOnStandbyList = this.flight.getStandbyList().pop();
                String newPassengerId = "FL" + nextOnStandbyList.getFlightNumber() + "-" +
                        flight.getDepartureTime() + "-" + (numberOfBookedPassengersOnBoardingList + 1);
                nextOnStandbyList.setDepartureTime(flight.getDepartureTime());
                nextOnStandbyList.setId(newPassengerId);
                this.flight.addToBoardingList(this.flight.getStandbyList().pop());
            }
            for (Passenger passenger : this.flight.getStandbyList()) {
                model.rescheduleStandbyPassenger(passenger);
            }
        }
        return this.getTimestamp();
    }
}
