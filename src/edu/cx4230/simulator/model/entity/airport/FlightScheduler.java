package edu.cx4230.simulator.model.entity.airport;

import edu.cx4230.simulator.model.events.airport.Arrival;
import edu.cx4230.simulator.structs.FutureEventList;
import edu.cx4230.simulator.util.Constants;
import edu.cx4230.simulator.util.Distributions;
import edu.cx4230.simulator.util.Print;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlightScheduler {

    private FutureEventList futureEventList;
    private List<Flight> listOfFlights;
    private int numFlightsScheduled = 0;
    private int numRoutesScheduled = 0;

    public FlightScheduler(int numberOfFlights, int flightsPerRoute) {
        Print.line("Attempting to create " + numberOfFlights + " flights with approximately " + flightsPerRoute + " flights per route");
        this.futureEventList = new FutureEventList();
        this.listOfFlights = new ArrayList<>(numberOfFlights);
        this.scheduleFlights(numberOfFlights, flightsPerRoute);
    }

    public FutureEventList getEventList() { return this.futureEventList; }

    public List<Flight> getFlights() { return this.listOfFlights; }

    private void scheduleFlights(int numberOfFlights, int flightsPerRoute) {
        int flightsScheduled = 0;
        int flightNumber = 0;
        while (flightsScheduled < numberOfFlights) {
            this.numRoutesScheduled++;
            for (int i = 0; flightsScheduled < numberOfFlights && i < flightsPerRoute; i++) {
                FlightGenerator flightGenerator = new FlightGenerator(flightNumber, Distributions.flightDepartureDistribution());
                Print.writer(flightGenerator.getFlight());
                this.listOfFlights.add(flightGenerator.getFlight());
                // TODO -> add the boardingdoorclose event here
                for (Passenger passenger : flightGenerator.getPassengerList()) {
                    this.futureEventList.push(new Arrival(passenger));
                }
                flightsScheduled++;
                this.numFlightsScheduled++;
            }
        }
    }

    // test methods
    public int getNumFlightsScheduled() { return this.numFlightsScheduled; }
    public int getNumRoutesScheduled() { return this.numRoutesScheduled; }

}
