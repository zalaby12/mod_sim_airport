package edu.cx4230.simulator.model.models;

import edu.cx4230.simulator.model.events.Event;
import edu.cx4230.simulator.structs.FutureEventList;

public interface Modeler {

    int processEvent(Event event);

    FutureEventList getFutureEventsList();


}
