package edu.cx4230.simulator.sim;

import edu.cx4230.simulator.model.models.Modeler;
import edu.cx4230.simulator.structs.FutureEventList;

public abstract class SimulationEngine {
  // probably need to make this a long??
  private int currentTime;
  private Modeler modeler;
  private FutureEventList futureEventList;

  SimulationEngine(Modeler modeler) {
    this.modeler = modeler;
    this.futureEventList = this.modeler.getFutureEventsList();
    this.currentTime = 0;
  }

  public void runSimulation() {
    while (this.futureEventList.peek() != null) {
        this.currentTime = this.modeler.processEvent(this.futureEventList.pop());
    }
  }
  
}
