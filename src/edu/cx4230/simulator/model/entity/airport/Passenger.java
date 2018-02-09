package edu.cx4230.simulator.model.entity.airport;

import edu.cx4230.simulator.util.Constants;

public class Passenger implements Comparable<Passenger> {

    private int arrivalTime;
    private int flightNumber;
    private int departureTime;
    private int ticketPrice;
    private int compensationAmount;
    private String id;
    private boolean hasBeenScheduledToArrive;
    private PassengerStatus passengerStatus;

    private Passenger(Builder builder) {
        this.arrivalTime = builder.arrivalTime;
        this.flightNumber = builder.flightNumber;
        this.departureTime = builder.departureTime;
        this.ticketPrice = builder.ticketPrice;
        this.passengerStatus = builder.passengerStatus;
        this.id = builder.id;
        this.compensationAmount = 0;
        this.hasBeenScheduledToArrive = false;
    }

    public int getArrivalTime() { return this.arrivalTime; }

    public int getFlightNumber() { return this.flightNumber; }

    public int getDepartureTime() { return this.departureTime; }

    public PassengerStatus getPassengerStatus() { return this.passengerStatus; }

    public int getCompensationAmount() { return this.compensationAmount; }

    public void addToTicketPrice(int value) { this.ticketPrice += value; }

    public void addToCompensationAmount(int value) { this.compensationAmount += value; }

    public void setDepartureTime(int value) { this.departureTime = value; }

    public void setId(String id) { this.id = id; }

    public void upgradeOverbookedToStandby() { this.passengerStatus = PassengerStatus.S0; }

    static class Builder {

        private int arrivalTime;
        private int flightNumber;
        private int departureTime;
        private int ticketPrice;
        private String id;
        private PassengerStatus passengerStatus;
        Builder arrivalTime(int arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        Builder flightNumber(int flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        Builder departureTime(int departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        Builder ticketPrice(int ticketPrice) {
            this.ticketPrice = ticketPrice;
            return this;
        }

        Builder passengerStatus(PassengerStatus passengerStatus) {
            this.passengerStatus = passengerStatus;
            return this;
        }

        Builder id(String id) {
            this.id = id;
            return this;
        }

        Passenger build() {
            return new Passenger(this);
        }
    }

    @Override
    public int compareTo(Passenger passenger) {
        return passenger.passengerStatus.asInt() - this.passengerStatus.asInt();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Passenger) {
            Passenger temp = (Passenger) other;
            if (temp.arrivalTime == this.arrivalTime &&
                    temp.flightNumber == this.flightNumber &&
                    temp.departureTime == this.departureTime &&
                    temp.ticketPrice == this.ticketPrice &&
                    temp.passengerStatus == this.passengerStatus &&
                    temp.compensationAmount == this.compensationAmount &&
                    this.id.equals(temp.id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String toString = "";
        toString += "Passenger " + arrivalTime + " for flight FL" + flightNumber + "-" + departureTime + "\n";
        toString += "Scheduled to arrive " + (arrivalTime >= departureTime - Constants.BOARDING_DOOR_CLOSE_WINDOW ? "late" : "on time") + "\n";
        toString += "Paid $" + ticketPrice + " and has status " + passengerStatus + "\n";
        return toString;
    }
}
