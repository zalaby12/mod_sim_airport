package edu.cx4230.simulator.sim;

import edu.cx4230.simulator.model.models.Modeler;

/*
 * Generic simulation engine that can be used for any DES that conforms to the
 * given interfaces.
 */
public abstract class SimulationEngine {
  // probably need to make this a long??
  private int currentTime;
  private Modeler modeler;

  SimulationEngine(Modeler modeler) {
    this.modeler = modeler;
    this.currentTime = 0;
  }

  // the simulation will run each event
  // if there is any problem with an event, it can return -1
  public void runSimulation() {
    while (this.modeler.hasMoreEvents()) {
        int response = this.modeler.processNextEvent();
        this.currentTime = response == -1 ? currentTime : response;
    }
  }

  public void printResults() {
      modeler.printResults();
  }
  
}
