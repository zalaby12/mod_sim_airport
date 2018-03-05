package edu.cx4230.simulator.util;

/*
 * Constants used throughout the app. More useful to give them a descriptive name
 * here than just random numbers in a script somewhere used for input. This will let
 * me play with the simulation pretty easily for the final report.
 */
public class Constants {

    public static final int NUMBER_OF_FLIGHTS = 850;

    public static final int NUMBER_OF_FLIGHTS_PER_ROUTE = 10;

    public static final double PERCENT_ON_TIME = 0.95;

    public static final double PERCENT_OVERBOOKED = 0.05;

    public static final double PERCENT_NON_REV_PASSENGERS_PER_FLIGHT = 0.10;

    public static final int ARRIVAL_WINDOW = 120;

    public static final int BOARDING_DOOR_CLOSE_WINDOW = 15;

    // starting this at 509.14 but it's a bit high.
    // 330 works well for income before rescheduling passengers
    // that's what we are going to use... for THIS study, we are only considering
    // the effects revenue from scheduling... so we want to see what the airline
    // is planning on earning from just seat sales, and we only know what they
    // earn, not what they pay out. our number is slightly over, as expected
    public static final double TICKET_PRICE_MEAN = 330.50;

    // starting this at 100
    public static final double TICKET_PRICE_STANDARD_DEVIATION = 50;

    public static final double COMPENSATION_AMOUNT_MEAN = 891;

    public static final double COMPENSATION_AMOUNT_STANDARD_DEVIATION = 50;

    public static final int EVENT_ERROR_CODE = -1;

}
