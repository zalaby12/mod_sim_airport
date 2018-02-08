package edu.cx4230.simulator.model.entity.airport;

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
