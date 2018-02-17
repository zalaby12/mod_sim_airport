import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.Suite;

/*
 * It is what it says it is.
 */
@RunWith(Suite.class)
@SuiteClasses(value = {
        DistributionTest.class,
        PriorityQueueTest.class,
        SchedulingTest.class
})

public abstract class TestSuite {}
