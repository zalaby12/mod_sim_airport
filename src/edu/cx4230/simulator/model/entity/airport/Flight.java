package edu.cx4230.simulator.model.entity.airport;

import edu.cx4230.simulator.structs.PriorityQueue;
import edu.cx4230.simulator.structs.Set;

public class Flight {

    private int flightNumber;
    private int departureTime;
    private int capacity;
    private boolean boardingDoorIsClosed;
    private Set<Passenger> bookedPassengers;
    private Set<Passenger> boardingList;
    private PriorityQueue<Passenger> standbyList;

    private Flight(Builder builder) {
        this.flightNumber = builder.flightNumber;
        this.departureTime = builder.departureTime;
        this.capacity = builder.capacity;
        this.boardingDoorIsClosed = builder.boardingDoorIsClosed;
        this.bookedPassengers = builder.bookedPassengers;
        this.boardingList = new Set<>();
        this.standbyList = new PriorityQueue<>();
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

}
