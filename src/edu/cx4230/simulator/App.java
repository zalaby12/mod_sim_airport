package edu.cx4230.simulator;

import edu.cx4230.simulator.sim.AirportSimulationEngine;
import edu.cx4230.simulator.sim.SimulationEngine;
import edu.cx4230.simulator.util.Print;

import static edu.cx4230.simulator.util.Constants.NUMBER_OF_FLIGHTS;
import static edu.cx4230.simulator.util.Constants.NUMBER_OF_FLIGHTS_PER_ROUTE;

public class App {

  public static void main(String[] args) {

    long start = System.currentTimeMillis();

    SimulationEngine engine = new AirportSimulationEngine(NUMBER_OF_FLIGHTS, NUMBER_OF_FLIGHTS_PER_ROUTE);
    runSimulation(engine);

    long end = System.currentTimeMillis();
    Print.line("The simulation took " + ((end - start) / 1000.0) + " seconds");

  }

  private static void runSimulation(SimulationEngine engine) { engine.runSimulation(); }

  public static boolean debugOn() {
      return true;
  }

}
