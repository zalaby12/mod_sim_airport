package edu.cx4230.simulator.model.entity.airport;

import edu.cx4230.simulator.structs.Set;
import edu.cx4230.simulator.util.Distributions;
import edu.cx4230.simulator.util.Print;

import static edu.cx4230.simulator.util.Constants.ARRIVAL_WINDOW;

/*
 * This class will create a single passenger list based on an exponential distribution
 * for arrival time and a normal distribution for ticket prices. It also schedules
 * standby passengers based on a narrow Gaussian distribution.
 *
 */
public class PassengerListGenerator {

    private Set<Passenger> bookedPassengers;
    private int revenueFromBookedList;
    private PassengerStatus[] status = PassengerStatus.values();

    public PassengerListGenerator(int flightNumber, int departureTime, int capacity) {
        this.revenueFromBookedList = 0;
        this.bookedPassengers = bookPassengers(flightNumber, departureTime, capacity);
    }

    private Set<Passenger> bookPassengers(int flightNumber, int departureTime, int capacity) {
        int numberOfPassengersBooked = Distributions.passengerDensityDistribution(capacity);
        int id = 0;
        Set<Passenger> tempSet = new Set<>();
        for (int i = 0; i < numberOfPassengersBooked; i++, id++) {
            Passenger passenger = new Passenger.Builder()
                    .arrivalTime(Distributions.passengerArrivalDistribution(departureTime, false))
                    .flightNumber(flightNumber)
                    .departureTime(departureTime)
                    .ticketPrice(Distributions.ticketPriceDistribution())
                    .passengerStatus(PassengerStatus.REV)
                    .id("FL" + flightNumber + "-" + departureTime + "-" + id)
                    .build();
            this.revenueFromBookedList += passenger.getTicketPrice();
            tempSet.add(passenger);
        }

        int numberOfStandbyPassengersScheduled = Distributions.standbyListDensityDistribution(capacity);
        for (int i = 0; i < numberOfStandbyPassengersScheduled; i++, id++) {
            PassengerStatus standbyStatus;
            do {
                standbyStatus = status[Distributions.uniformDistributionWithLimit(status.length)];
            }
            while (standbyStatus == PassengerStatus.REV || standbyStatus == PassengerStatus.S0);
            tempSet.add(new Passenger.Builder()
                    .arrivalTime(Distributions.passengerArrivalDistribution(departureTime, false))
                    .flightNumber(flightNumber)
                    .departureTime(departureTime)
                    .ticketPrice(0)
                    .passengerStatus(standbyStatus)
                    .id("FL" + flightNumber + "-" + departureTime + "-" + id)
                    .build());
        }
        Print.line("The flight FL" + flightNumber + "-" + departureTime + " has capacity " +
                capacity + " and will be booked with " + numberOfPassengersBooked + "" +
                " revenue passengers and " + numberOfStandbyPassengersScheduled + " non-rev passengers");
        return tempSet;
    }

    public Set<Passenger> getList() {
        return this.bookedPassengers;
    }

    public int getRevenueFromBookedList() {
        Print.line("total revenue for this passenger list is: " + this.revenueFromBookedList);
        return this.revenueFromBookedList;
    }
}
