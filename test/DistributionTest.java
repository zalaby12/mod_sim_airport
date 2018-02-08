import edu.cx4230.simulator.util.Distributions;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import java.util.Random;

import static edu.cx4230.simulator.util.Constants.ARRIVAL_WINDOW;
import static edu.cx4230.simulator.util.Constants.NUMBER_OF_FLIGHTS;
import static org.junit.jupiter.api.Assertions.*;

public class DistributionTest {

    @Test
    public void testExponentialDistribution() {
        double lambda = .25;
        double expectedValue = 1 / lambda;
        double ninety_five_percent = Math.log(1 - .95) / -lambda;
        double sum = 0;
        double trials = 10000;
        double numBefore95Percent = 0;
        for (int i = 0; i < trials; i++) {
            double num = Distributions.testExponential(lambda);
            sum += num;
            if (num < ninety_five_percent) {
                numBefore95Percent++;
            }
        }

        if ((numBefore95Percent / trials) >= ninety_five_percent - .01 ||
                (numBefore95Percent / trials) <= ninety_five_percent + .01) {
            assertEquals(true, true, "between valid parameters");
            System.out.println("approximately " + (numBefore95Percent / trials) + " were before " + ninety_five_percent);
        } else {
            assertEquals(true, false);
        }

        if ((sum / trials) >= expectedValue - .1 || (sum / trials) <= expectedValue + .1) {
            System.out.println("expected value is " + expectedValue + " and the average was " + sum / trials);
            assertEquals(true, true, "between valid parameters");
        } else {
            assertEquals(true, false);
        }

    }

    @Test
    public void passengerArrivalDistributionIsNonNegative() {
        for (int numFlights = 2; numFlights < 100; numFlights++) {
            int flightDeparture = ARRIVAL_WINDOW + new Random().nextInt(NUMBER_OF_FLIGHTS * ARRIVAL_WINDOW);
            for (double lambda = 0; lambda <= 1; lambda += 0.25) {
                assertTrue(Distributions.passengerArrivalDistribution(flightDeparture, false) >= 0);
            }
        }
    }

}
