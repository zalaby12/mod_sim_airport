package edu.cx4230.simulator.model.models;


import edu.cx4230.simulator.model.entity.airport.Flight;
import edu.cx4230.simulator.model.entity.airport.FlightScheduler;
import edu.cx4230.simulator.model.entity.airport.Passenger;
import edu.cx4230.simulator.model.events.airport.AirportEvent;
import edu.cx4230.simulator.model.events.Event;
import edu.cx4230.simulator.model.events.airport.RescheduleLateArrival;
import edu.cx4230.simulator.structs.FutureEventList;
import edu.cx4230.simulator.util.Print;

import java.util.List;

// Classes that extend model can be the information holders, since they don't
// do much of anything, anyway.
public class AirportModel implements Modeler {

    private List<Flight> flights;
    private FlightScheduler scheduler;

    // this needs to populate this map for later.
    // it should also probably have a map from flight number to
    // a queue of flights (in order they leave)
    public AirportModel(int numberOfFlights, int flightsPerRoute) {
        this.scheduler = new FlightScheduler(numberOfFlights, flightsPerRoute);
        this.flights = scheduler.getFlights();
        Print.line("the model scheduled " + this.flights.size() + " flights and " + this.scheduler.getNumPassengers() + " passengers.");
    }

    @Override
    public int processEvent(Event event) {
        return this.processEvent(this, (AirportEvent) event);
    }

    public FutureEventList getFutureEventsList() {
        return scheduler.getEventList();
    }

    public Flight getFlight(int flightNumber, int departureTime) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber
                    && flight.getDepartureTime() == departureTime) {
                return flight;
            }
        }
        return null;
    }

    public Flight getNextFlight(int flightNumber, int departureTime) {
        int min = Integer.MAX_VALUE;
        Flight nextFlight = null;
        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber &&
                    flight.getDepartureTime() >= departureTime &&
                    flight.getDepartureTime() < min &&
                    !flight.boardingDoorIsClosed()) {
                min = flight.getDepartureTime();
                nextFlight = flight;
            }
        }
        return nextFlight;
    }

    private int processEvent(AirportModel model, AirportEvent event) {
        return event.executeEvent(model);
    }

    public void reschedulePassenger(Passenger passenger) {
        this.scheduler.getEventList().push(new RescheduleLateArrival(passenger));
    }

}
