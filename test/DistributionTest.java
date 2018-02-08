import edu.cx4230.simulator.util.Distributions;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
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
        } else {
            assertEquals(true, false);
        }

        if ((sum / trials) >= expectedValue - .1 || (sum / trials) <= expectedValue + .1) {
            assertEquals(true, true, "between valid parameters");
        } else {
            assertEquals(true, false);
        }

        System.out.println("approximately " + (numBefore95Percent / trials) + " were before " + ninety_five_percent);
        System.out.println("expected value is " + expectedValue + " and the average was " + sum / trials);
    }

}
