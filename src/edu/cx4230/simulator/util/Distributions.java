package edu.cx4230.simulator.util;

import java.util.Random;

import static edu.cx4230.simulator.util.Constants.*;

public class Distributions {

    private static Random generator = new Random();

    public static int flightDepartureDistribution() {
        return ARRIVAL_WINDOW + generator.nextInt(NUMBER_OF_FLIGHTS * ARRIVAL_WINDOW);
    }

    public static int passengerArrivalDistribution(int flightDepartureTime, boolean basedOnBoardingDoorClose) {
        int timestamp = basedOnBoardingDoorClose ? flightDepartureTime - BOARDING_DOOR_CLOSE_WINDOW : flightDepartureTime;
        double lambda = lambda(PERCENT_ON_TIME, ARRIVAL_WINDOW);
        return (int) Math.round(timestamp - ARRIVAL_WINDOW + exponentialDistribution(lambda));
    }

    public static int uniformDistributionWithLimit(int limit) {
        return generator.nextInt(limit);
    }

    // this is a BIG guess, but I want the number of passengers to follow a gaussian
    // that's centered on the capacity and includes the typical overbooking amount in the
    // first Standard Deviation (hence the first line...). it looks like this will also
    // typically put the max overbooking amount (15%) just outside the third standard
    // deviation, which makes me think this is on the right track
    // this also happens to give a nice lower bound for the number of passengers
    public static int passengerDensityDistribution(int capacity) {
        int differenceBetweenCapacityAndStandardOverbooking = (int) Math.round((capacity * (1 + PERCENT_OVERBOOKED)) - capacity);
        return (int) Math.round(gaussianDistribution((capacity * (1 + PERCENT_OVERBOOKED)), differenceBetweenCapacityAndStandardOverbooking));
    }

    // also a pretty hefty guess, but we are going to assume the number of people
    // on the standby list follows another normal with just a percentage of the
    // capacity of the plane and a standard deviation of 1.
    public static int standbyListDensityDistribution(int capacity) {
        return (int) Math.round(gaussianDistribution(capacity * PERCENT_NON_REV_PASSENGERS_PER_FLIGHT, 1));
    }

    public static int ticketPriceDistribution() {
        return (int) Math.round(gaussianDistribution(TICKET_PRICE_MEAN, TICKET_PRICE_STANDARD_DEVIATION));
    }

    public static int compensationDistribution() {
        return (int) Math.round(gaussianDistribution(COMPENSATION_AMOUNT_MEAN, COMPENSATION_AMOUNT_STANDARD_DEVIATION));
    }

    public static double testExponential(double lambda) {
        return exponentialDistribution(lambda);
    }

    // for 95% of people to arrive before the flight 120 minutes after "arriving" starts
    // (aka after x = 120 minutes) you solve F(x) = 1 - exp(-lambda * x)
    // which gives you lambda = ln(1 - F(x)) / (-x)

    private static double lambda(double fx, int x) {
        return Math.log(1 - fx) / -x;
    }

    private static double exponentialDistribution(double lambda) {
        return Math.log(1 - generator.nextDouble()) / -lambda;
    }

    // via the general theory of random variables
    private static double gaussianDistribution(double mean, double standardDeviation) {
       return (generator.nextGaussian() * standardDeviation) + mean ;
    }

}
