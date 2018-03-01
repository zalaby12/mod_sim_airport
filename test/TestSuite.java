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
        SchedulingTest.class,
        LinkedListTest.class,
        LinkedListTestStudent.class
})

public abstract class TestSuite {}
