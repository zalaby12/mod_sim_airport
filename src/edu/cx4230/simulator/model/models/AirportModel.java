package edu.cx4230.simulator.model.models;


import edu.cx4230.simulator.model.entity.airport.Flight;
import edu.cx4230.simulator.model.entity.airport.FlightScheduler;
import edu.cx4230.simulator.model.entity.airport.Passenger;
import edu.cx4230.simulator.model.events.airport.AirportEvent;
import edu.cx4230.simulator.model.events.Event;
import edu.cx4230.simulator.model.events.airport.RescheduleLateArrival;
import edu.cx4230.simulator.model.events.airport.RescheduleOverbookedPassenger;
import edu.cx4230.simulator.model.events.airport.RescheduleStandbyPassenger;
import edu.cx4230.simulator.structs.FutureEventList;
import edu.cx4230.simulator.structs.Set;
import edu.cx4230.simulator.util.Print;

import java.util.List;

// Classes that extend model can be the information holders, since they don't
// do much of anything, anyway.
public class AirportModel implements Modeler {

    private List<Flight> flights;
    private FlightScheduler scheduler;
    private FutureEventList futureEventList;
    private Set<Passenger> overbookedPassengers;

    public AirportModel(int numberOfFlights, int flightsPerRoute) {
        this.scheduler = new FlightScheduler(numberOfFlights, flightsPerRoute);
        this.flights = scheduler.getFlights();
        this.futureEventList = this.scheduler.getEventList();
        this.overbookedPassengers = new Set<>();
        Print.line("the model scheduled " + this.flights.size() + " flights and " + this.scheduler.getNumPassengers() + " passengers.");
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

    public void addOverbookedPassenger(Passenger passenger) { this.overbookedPassengers.add(passenger); }

    public void rescheduleLateArrival(Passenger passenger) {
        this.futureEventList.push(new RescheduleLateArrival(passenger));
    }

    public void rescheduleOverbookedPassenger(Passenger passenger) {
        this.futureEventList.push(new RescheduleOverbookedPassenger(passenger));
    }

    public void rescheduleStandbyPassenger(Passenger passenger) {
        this.futureEventList.push(new RescheduleStandbyPassenger(passenger));
    }

    @Override
    public int processNextEvent() {
        return ((AirportEvent) futureEventList.pop()).executeEvent(this);
    }

    @Override
    public boolean hasMoreEvents() { return this.futureEventList.size() > 0; }

    @Override
    public void printResults() {
        System.out.println("\n\n\n");
        System.out.println("There were " + this.overbookedPassengers.size() +
                " overbooked passengers out of " + this.scheduler.getNumPassengers());
        System.out.println("Percent overbooked: " + (10.0 * this.overbookedPassengers.size() / this.scheduler.getNumPassengers()));
        System.out.println("Their total compensation was " + this.totalCompensation());
    }

    private int totalCompensation() {
        int sum = 0;
        for (Passenger passenger : overbookedPassengers) {
            sum += passenger.getCompensationAmount();
        }
        return sum;
    }

}
