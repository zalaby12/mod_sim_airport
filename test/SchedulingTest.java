import edu.cx4230.simulator.model.entity.airport.FlightScheduler;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchedulingTest {

    /*
     * A test to make sure that 1 route is scheduled and 10 flights are scheduled for that
     * route. The math in the scheduling algorithm one schedules one route at a time
     * so we know that if 10 flights are scheduled and only one route exists, they
     * must logically belong to that route
     */
    @Test
    public void testBasicScheduleNumber() {
        FlightScheduler scheduler = new FlightScheduler(10, 10);
        assertEquals(1, scheduler.getNumRoutesScheduled());
        assertEquals(10, scheduler.getNumFlightsScheduled());
    }

    /*
     * Bounds testing to ensure scheduling a non-full route works in the program
     */
    @Test
    public void testSchedulerWithLittleFlightsPerRoute() {
        FlightScheduler scheduler = new FlightScheduler(6, 10);
        assertEquals(1, scheduler.getNumRoutesScheduled());
        assertEquals(6, scheduler.getNumFlightsScheduled());
    }

    /*
     * Bounds testing to ensure scheduling more than one route will fill the first
     * and then just partially fill the second route.
     */
    @Test
    public void testSchedulerWithNumberOfFlightsNotDivisibleByFlightsPerRoute() {
        FlightScheduler scheduler = new FlightScheduler(13, 10);
        assertEquals(2, scheduler.getNumRoutesScheduled());
        assertEquals(13, scheduler.getNumFlightsScheduled());
    }

    /*
     * This tests that the scheduler can handle scheduling with the large amount
     * of objects laying around in memory. Future testing might impose some kind
     * of time constraint on the time it would take to schedule these routes.
     */
    @Test
    public void stressTestAmount() {
        FlightScheduler scheduler = new FlightScheduler(100, 10);
        assertEquals(10, scheduler.getNumRoutesScheduled());
        assertEquals(100, scheduler.getNumFlightsScheduled());
    }
}
