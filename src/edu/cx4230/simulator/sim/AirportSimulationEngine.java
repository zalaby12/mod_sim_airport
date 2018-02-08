package edu.cx4230.simulator.sim;

import edu.cx4230.simulator.model.models.AirportModel;

public class AirportSimulationEngine extends SimulationEngine {

    public AirportSimulationEngine(int numberOfFlights, int flightPerRoute) {
        super(new AirportModel(numberOfFlights, flightPerRoute));
    }

}
