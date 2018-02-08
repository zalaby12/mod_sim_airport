package edu.cx4230.simulator.model.entity.airport;

public class Passenger implements Comparable<Passenger> {

    private int arrivalTime;
    private int flightNumber;
    private int departureTime;
    private int ticketPrice;
    private int compensationAmount;
    private boolean hasBeenScheduledToArrive;
    private PassengerStatus passengerStatus;

    private Passenger(Builder builder) {
        this.arrivalTime = builder.arrivalTime;
        this.flightNumber = builder.flightNumber;
        this.departureTime = builder.departureTime;
        this.ticketPrice = builder.ticketPrice;
        this.passengerStatus = builder.passengerStatus;
        this.compensationAmount = 0;
        this.hasBeenScheduledToArrive = false;
    }

    @Override
    public int compareTo(Passenger passenger) {
        return passenger.passengerStatus.asInt() - this.passengerStatus.asInt();
    }

    public int getArrivalTime() { return this.arrivalTime; }

    static class Builder {
        private int arrivalTime;
        private int flightNumber;
        private int departureTime;
        private int ticketPrice;
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

        Passenger build() {
            return new Passenger(this);
        }
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
                    temp.compensationAmount == this.compensationAmount) {
                return true;
            }
        }
        return false;
    }

}
