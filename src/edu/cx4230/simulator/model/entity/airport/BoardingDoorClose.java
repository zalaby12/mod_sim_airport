package edu.cx4230.simulator.model.entity.airport;

import edu.cx4230.simulator.model.events.airport.AirportEvent;
import edu.cx4230.simulator.model.models.AirportModel;
import edu.cx4230.simulator.util.Constants;

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
