package edu.cx4230.simulator.model.entity.airport;

import edu.cx4230.simulator.structs.Set;
import edu.cx4230.simulator.util.Distributions;
import edu.cx4230.simulator.util.Print;

import static edu.cx4230.simulator.util.Constants.ARRIVAL_WINDOW;

public class PassengerListGenerator {

    private Set<Passenger> bookedPassengers;
    private PassengerStatus[] status = PassengerStatus.values();

    // TODO write a test for the set to make sure it works right.... fuck meeeeee
    public PassengerListGenerator(int flightNumber, int departureTime, int capacity) {
        this.bookedPassengers = bookPassengers(flightNumber, departureTime, capacity);
    }

    private Set<Passenger> bookPassengers(int flightNumber, int departureTime, int capacity) {
        int numberOfPassengersBooked = Distributions.passengerDensityDistribution(capacity);
        Set<Passenger> tempSet = new Set<>();
        for (int i = 0; i < numberOfPassengersBooked; i++) {
            tempSet.add(new Passenger.Builder()
                    .arrivalTime(departureTime - ARRIVAL_WINDOW + Distributions.passengerArrivalDistribution(flightNumber, false))
                    .flightNumber(flightNumber)
                    .departureTime(departureTime)
                    .ticketPrice(Distributions.ticketPriceDistribution())
                    .passengerStatus(PassengerStatus.REV)
                    .build());
        }

        int numberOfStandbyPassengersScheduled = Distributions.standbyListDensityDistribution(capacity);
        for (int i = 0; i < numberOfStandbyPassengersScheduled; i++) {
            PassengerStatus standbyStatus;
            do {
                standbyStatus = status[Distributions.uniformDistributionWithLimit(status.length)];
            }
            while (standbyStatus == PassengerStatus.REV || standbyStatus == PassengerStatus.S0);
            tempSet.add(new Passenger.Builder()
                    .arrivalTime(Distributions.passengerArrivalDistribution(flightNumber, false))
                    .flightNumber(flightNumber)
                    .departureTime(departureTime)
                    .ticketPrice(0)
                    .passengerStatus(standbyStatus)
                    .build());
        }
        Print.line("The flight FL" + flightNumber + "-" + departureTime + " has capacity " +
                capacity + " and will be booked with " + numberOfPassengersBooked + "" +
                " revenue passengers and " + numberOfStandbyPassengersScheduled + " non-rev passengers");
        return tempSet;
    }

    public Set<Passenger> getList() {
        return bookedPassengers;
    }
}
