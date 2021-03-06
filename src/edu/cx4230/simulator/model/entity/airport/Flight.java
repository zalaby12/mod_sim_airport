package edu.cx4230.simulator.model.entity.airport;

import edu.cx4230.simulator.structs.PriorityQueue;
import edu.cx4230.simulator.structs.Set;
import edu.cx4230.simulator.structs.StandbyList;
import edu.cx4230.simulator.util.Writeable;

/*
 * Models a flight using the basic information listed below as private variables.
 * The class includes it's own builder class to aid with construction during
 * flight generation. Mostly contains simple getters, and a few setters.
 */
public class Flight implements Writeable {

    private int flightNumber;
    private int departureTime;
    private int capacity;
    private boolean boardingDoorIsClosed;
    private Set<Passenger> bookedPassengers;
    private Set<Passenger> boardingList;
    private StandbyList standbyList;

    private Flight(Builder builder) {
        this.flightNumber = builder.flightNumber;
        this.departureTime = builder.departureTime;
        this.capacity = builder.capacity;
        this.boardingDoorIsClosed = builder.boardingDoorIsClosed;
        this.bookedPassengers = builder.bookedPassengers;
        this.boardingList = new Set<>();
        this.standbyList = new StandbyList();
    }

    static class Builder {

        private int flightNumber;
        private int departureTime;
        private int capacity;
        private boolean boardingDoorIsClosed;
        private Set<Passenger> bookedPassengers;
        Builder flightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        Builder departureTime(int departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        Builder boardingDoorIsClosed(boolean boardingDoorIsClosed) {
            this.boardingDoorIsClosed = boardingDoorIsClosed;
            return this;
        }

        Builder bookedPassengers(Set<Passenger> bookedPassengers) {
            this.bookedPassengers = bookedPassengers;
            return this;
        }

        Flight build() {
            return new Flight(this);
        }

    }
    public int getFlightNumber() { return this.flightNumber; }

    public int getDepartureTime() { return this.departureTime; }

    public Set<Passenger> getBookedPassengers() { return this.bookedPassengers; }

    public void closeBoardingDoor() { this.boardingDoorIsClosed = true; }

    public boolean boardingDoorIsClosed() { return this.boardingDoorIsClosed; }

    public int getCapacity() { return this.capacity; }

    public Set<Passenger> getBoardingList() { return this.boardingList; }

    public PriorityQueue<Passenger> getStandbyList() { return this.standbyList; }

    public int getSizeOfBoardingList() { return this.boardingList.size(); }

    public void addToBoardingList(Passenger passenger) { this.boardingList.add(passenger); }

    public void addToStandbyList(Passenger passenger) { this.standbyList.push(passenger); }

    public boolean isOverbooked() { return this.boardingList.size() > this.capacity; }

    @Override
    public String toString() {
        String toString = "";
        toString += "Flight number FL" + flightNumber + "-" + departureTime + "\n";
        toString += "Capacity: " + capacity + " passengers\n";
        toString += "Passenger List:\n";
        StringBuilder builder = new StringBuilder();
        for (Passenger passenger : bookedPassengers) {
            builder.append(passenger.toString());
            builder.append("\n");
        }
        toString += builder.toString() + "\n---------------------------------\n";
        return toString;
    }

    @Override
    public String fileName() {
        return "FL" + flightNumber + "-" + departureTime;
    }

    @Override
    public String fileText() {
        return this.toString();
    }

}
