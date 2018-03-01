package edu.cx4230.simulator.model.entity.airport;

import edu.cx4230.simulator.structs.Set;
import edu.cx4230.simulator.util.Distributions;

/*
 * This class will create a single flight based on a uniform distribution of
 * the flight capacities. It must be given a flight number and departure time.
 * It also creates a passenger list for the flight.
 */
public class FlightGenerator {
    private Flight flight;
    private FlightCapacity[] flightCapacities = FlightCapacity.values();
    private int revenueFromFlight;

    public FlightGenerator(int flightNumber, int departureTime) {
        int capacity = flightCapacities[Distributions.uniformDistributionWithLimit(flightCapacities.length)].getCapacity();
        PassengerListGenerator generator = new PassengerListGenerator(flightNumber, departureTime, capacity);
        this.flight  = new Flight.Builder()
                .flightNumber(flightNumber)
                .departureTime(departureTime)
                .capacity(capacity)
                .boardingDoorIsClosed(false)
                .bookedPassengers(generator.getList())
                .build();
        this.revenueFromFlight = generator.getRevenueFromBookedList();
    }

    public Flight getFlight() {
        return this.flight;
    }

    public int getRevenueFromFlight() { return this.revenueFromFlight; }

    public Set<Passenger> getPassengerList() {
        return this.flight.getBookedPassengers();
    }

}
