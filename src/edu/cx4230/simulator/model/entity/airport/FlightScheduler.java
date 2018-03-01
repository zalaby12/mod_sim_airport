package edu.cx4230.simulator.model.entity.airport;

import edu.cx4230.simulator.model.events.Event;
import edu.cx4230.simulator.model.events.airport.Arrival;
import edu.cx4230.simulator.model.events.airport.BoardingDoorClose;
import edu.cx4230.simulator.structs.FutureEventList;
import edu.cx4230.simulator.util.Distributions;
import edu.cx4230.simulator.util.Print;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * This class schedules flight departures uniformly in an observation space
 * of size 120 + (numberOfFlights * 120). This ensures all passengers will
 * no earlier than two hours before departure and makes flights (more or less)
 * independent. Flight departures are scheduled before arrival events so that
 * passengers must arrive BEFORE the departure time. Once the flights are scheduled,
 * the boardingDoorClose events and Arrival events are kept in an FEL to be returned
 * to the model
 */
public class FlightScheduler {

    private FutureEventList futureEventList;
    private List<Flight> listOfFlights;
    private int numFlightsScheduled = 0;
    private int numRoutesScheduled = 0;
    private int numPassengers = 0;
    private int revenueFromAllFlights;

    public FlightScheduler(int numberOfFlights, int flightsPerRoute) {
        Print.line("Attempting to create " + numberOfFlights + " flights with approximately " + flightsPerRoute + " flights per route");
        this.futureEventList = new FutureEventList();
        this.listOfFlights = new ArrayList<>(numberOfFlights);
        this.revenueFromAllFlights = 0;
        this.scheduleFlights(numberOfFlights, flightsPerRoute);
    }

    public FutureEventList getEventList() { return this.futureEventList; }

    public List<Flight> getFlights() { return this.listOfFlights; }

    private void scheduleFlights(int numberOfFlights, int flightsPerRoute) {
        int flightsScheduled = 0;
        int flightNumber = 0;
        List<Event> allEvents = new ArrayList<>();
        while (flightsScheduled < numberOfFlights) {
            this.numRoutesScheduled++;
            for (int i = 0; flightsScheduled < numberOfFlights && i < flightsPerRoute; i++) {
                FlightGenerator flightGenerator = new FlightGenerator(flightNumber, Distributions.flightDepartureDistribution());
                this.revenueFromAllFlights += flightGenerator.getRevenueFromFlight();
                Print.writer(flightGenerator.getFlight());
                this.listOfFlights.add(flightGenerator.getFlight());
                allEvents.add(new BoardingDoorClose(flightGenerator.getFlight()));
                for (Passenger passenger : flightGenerator.getPassengerList()) {
                    if (passenger.getPassengerStatus() == PassengerStatus.REV) {
                        numPassengers++;
                    }
                    allEvents.add(new Arrival(passenger));
                }
                flightsScheduled++;
                this.numFlightsScheduled++;
            }
            flightNumber++;
        }
        allEvents.sort(new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o2.getTimestamp() - o1.getTimestamp();
            }
        });
        for (Event event : allEvents) {
            this.futureEventList.push(event);
        }
    }

    // test methods
    public int getNumFlightsScheduled() { return this.numFlightsScheduled; }
    public int getNumRoutesScheduled() { return this.numRoutesScheduled; }
    public int getNumPassengers() { return this.numPassengers; }
    public int getRevenueFromAllFlights() { return this.revenueFromAllFlights; }

}
