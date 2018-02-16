package edu.cx4230.simulator;

import edu.cx4230.simulator.sim.AirportSimulationEngine;
import edu.cx4230.simulator.sim.SimulationEngine;
import edu.cx4230.simulator.util.Constants;
import edu.cx4230.simulator.util.Print;

import static edu.cx4230.simulator.util.Constants.NUMBER_OF_FLIGHTS;
import static edu.cx4230.simulator.util.Constants.NUMBER_OF_FLIGHTS_PER_ROUTE;

// TODO -> figure out total made by the airlines... add to another group when they actually board the plane
public class App {

  public static boolean DEBUG_ON = false;

  public static void main(String[] args) {

    for (String arg : args) {
      if (arg.equals("-v")) {
        DEBUG_ON = true;
      }
    }

    long start = System.currentTimeMillis();

    SimulationEngine engine = new AirportSimulationEngine(NUMBER_OF_FLIGHTS, NUMBER_OF_FLIGHTS_PER_ROUTE);
    runSimulation(engine);
    printResults(engine);

    long end = System.currentTimeMillis();

    System.out.println("The simulation took " + ((end - start) / 1000.0) + " seconds");

  }

  private static void runSimulation(SimulationEngine engine) { engine.runSimulation(); }

  private static void printResults(SimulationEngine engine) { engine.printResults(); }

  public static boolean debugOn() { return DEBUG_ON; }

}
