package edu.cx4230.simulator.model.events.airport;

import edu.cx4230.simulator.model.events.Event;
import edu.cx4230.simulator.model.models.AirportModel;

/*
 * This event helps narrow the scope of execution for the airport simulation
 */
public abstract class AirportEvent extends Event {

    public AirportEvent(int timestamp) { super(timestamp);}

    public abstract int executeEvent(AirportModel model);

}
