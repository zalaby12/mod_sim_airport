package edu.cx4230.simulator.model.models;

public interface Modeler {

    int processNextEvent();

    boolean hasMoreEvents();

    void printResults();

}
