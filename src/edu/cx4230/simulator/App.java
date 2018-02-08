package edu.cx4230.simulator;

import edu.cx4230.simulator.sim.AirportSimulationEngine;
import edu.cx4230.simulator.sim.SimulationEngine;

import static edu.cx4230.simulator.util.Constants.NUMBER_OF_FLIGHTS;
import static edu.cx4230.simulator.util.Constants.NUMBER_OF_FLIGHTS_PER_ROUTE;

public class App {

  public static void main(String[] args) {

    System.out.println("Starting simulation...");

    SimulationEngine engine = new AirportSimulationEngine(NUMBER_OF_FLIGHTS, NUMBER_OF_FLIGHTS_PER_ROUTE);
    runSimulation(engine);

    System.out.println("Done.");

  }

  private static void runSimulation(SimulationEngine engine) {
    System.out.println("Running simulation...");
    engine.runSimulation();
  }

  public static boolean debugOn() {
      return true;
  }

}
