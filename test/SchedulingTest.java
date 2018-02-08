import edu.cx4230.simulator.model.entity.airport.FlightScheduler;
//import org.junit.jupiter.api.Test;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchedulingTest {

    @Test
    public void testBasicScheduleNumber() {
        FlightScheduler scheduler = new FlightScheduler(10, 10);
        assertEquals(1, scheduler.getNumRoutesScheduled());
        assertEquals(10, scheduler.getNumFlightsScheduled());
    }

    @Test
    public void testSchedulerWithLittleFlightsPerRoute() {
        FlightScheduler scheduler = new FlightScheduler(6, 10);
        assertEquals(1, scheduler.getNumRoutesScheduled());
        assertEquals(6, scheduler.getNumFlightsScheduled());
    }

    @Test
    public void testSchedulerWithNumberOfFlightsNotDivisibleByFlightsPerRoute() {
        FlightScheduler scheduler = new FlightScheduler(13, 10);
        assertEquals(2, scheduler.getNumRoutesScheduled());
        assertEquals(13, scheduler.getNumFlightsScheduled());
    }

    @Test
    public void stressTestAmount() {
        FlightScheduler scheduler = new FlightScheduler(100, 10);
        assertEquals(10, scheduler.getNumRoutesScheduled());
        assertEquals(100, scheduler.getNumFlightsScheduled());
    }
}
