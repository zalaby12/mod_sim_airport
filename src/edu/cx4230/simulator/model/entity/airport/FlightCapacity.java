package edu.cx4230.simulator.model.entity.airport;

// based on 747 typical seating per https://en.wikipedia.org/wiki/Boeing_747
public enum FlightCapacity {
    _747SP(276), _747_100(366), _747_200B(366), _747_300(400), _747_400ER(416), _747_8(467);

    private int capacity;

    FlightCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() { return this.capacity; }

}
