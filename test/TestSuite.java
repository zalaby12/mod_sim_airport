import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@SuiteClasses(value = {
        DistributionTest.class,
        PriorityQueueTest.class,
        SchedulingTest.class
})

public abstract class TestSuite {}
