package edu.cx4230.simulator.model.models;

/*
 * The basic interface for a model in my application.
 * In the future, will probably include it's own FEL instead of
 * pawning it off to subclasses.
 */
public interface Modeler {

    int processNextEvent();

    boolean hasMoreEvents();

    void printResults();

}
