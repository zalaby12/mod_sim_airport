package edu.cx4230.simulator.model.entity.airport;

/*
 * Passenger statuses used to differentiate preference throughout the simulation.
 * REV => paid for a ticket and has highest boarding priority
 * S0  => paid for a ticket and was overbooked, will have the next open seat on a flight.
 * S1 - S4 => standby passengers that have decreasing preference as the number increases.
 */
public enum PassengerStatus {

    REV(0), S0(1), S1(2), S2(3), S3(4), S4(5);

    private int status;

    PassengerStatus(int status) {
        this.status = status;
    }

    public int asInt() {
        return status;
    }

}
