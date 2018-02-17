package edu.cx4230.simulator.sim;

import edu.cx4230.simulator.model.models.AirportModel;

/*
 * Narrows down the model and simulation engine specific to the airport simulation.
 */
public class AirportSimulationEngine extends SimulationEngine {

    public AirportSimulationEngine(int numberOfFlights, int flightPerRoute) {
        super(new AirportModel(numberOfFlights, flightPerRoute));
    }

}
