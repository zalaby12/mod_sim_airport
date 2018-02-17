import edu.cx4230.simulator.model.entity.airport.PassengerStatus;
import edu.cx4230.simulator.structs.FutureEventList;
import edu.cx4230.simulator.structs.PriorityQueue;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/*
 * This tests the functionality of the priority queue using both built-in types
 * and types specific to the airport model/simulation
 */
public class PriorityQueueTest {

    @Test
    public void testBasicInsert() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        assertNull(priorityQueue.peek());
        for (int i = 0; i < 100; i++) {
            priorityQueue.push(i);
        }
        for (int i = priorityQueue.size() - 1; i >= 0; i--) {
            int num = priorityQueue.pop();
            assertEquals(num, i);
        }
        assertNull(priorityQueue.peek());
    }

    @Test
    public void testNegativeOneForSpecialEvents() {
        FutureEventList priorityQueue = new FutureEventList();
        assertNull(priorityQueue.peek());
        priorityQueue.push(new TestEvent(0));
        priorityQueue.push(new TestEvent(-1));
        priorityQueue.push(new TestEvent(2));

        assertEquals(-1, priorityQueue.pop().getTimestamp());
        assertEquals(0, priorityQueue.pop().getTimestamp());
        assertEquals(2, priorityQueue.pop().getTimestamp());
    }

    @Test
    public void basicInsertBackwards() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        assertNull(priorityQueue.peek());
        for (int i = 100; i >= 0; i--) {
            priorityQueue.push(i);
        }
        for (int i = priorityQueue.size() - 1; i >= 0; i--) {
            int num = priorityQueue.pop();
            assertEquals(num, i);
        }
        assertNull(priorityQueue.peek());
    }

    @Test
    public void testRandomInsert() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        assertNull(priorityQueue.peek());
        int[] randArr = new int[100];
        Random gen = new Random();
        for (int i = 0; i < 100; i++) {
            int temp = gen.nextInt();
            randArr[i] = temp;
            priorityQueue.push(temp);
        }
        Arrays.sort(randArr);
        for (int i = priorityQueue.size() - 1; i >= 0; i--) {
            int num = priorityQueue.pop();
            assertEquals(num, randArr[i]);
        }
        assertNull(priorityQueue.peek());
    }

    @Test
    public void emptyListReturnNull() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        assertNull(priorityQueue.peek());
        assertNull(priorityQueue.pop());
    }

    @Test
    public void testPriorityQueueOrderWithEvents() {
        PriorityQueue<TestEvent> priorityQueue = new PriorityQueue<>();
        assertNull(priorityQueue.peek());
        assertNull(priorityQueue.pop());
        priorityQueue.push(new TestEvent(1));
        priorityQueue.push(new TestEvent(3));
        priorityQueue.push(new TestEvent(5));
        priorityQueue.push(new TestEvent(7));
        priorityQueue.push(new TestEvent(0));
        priorityQueue.push(new TestEvent(2, 0));
        priorityQueue.push(new TestEvent(2, 1));

        assertEquals(0, priorityQueue.pop().getTimestamp());
        assertEquals(1, priorityQueue.pop().getTimestamp());

        TestEvent temp = priorityQueue.pop();
        assertEquals(2, temp.getTimestamp());
        assertEquals(0, temp.id);

        temp = priorityQueue.pop();
        assertEquals(2, temp.getTimestamp());
        assertEquals(1, temp.id);

        assertEquals(3, priorityQueue.pop().getTimestamp());
        assertEquals(5, priorityQueue.pop().getTimestamp());
        assertEquals(7, priorityQueue.pop().getTimestamp());
    }

    @Test
    public void testStandbyListOrder() {
        TestStandbyList standbyList = new TestStandbyList();
        TestPassenger pass;
        assertNull(standbyList.peek());
        assertNull(standbyList.pop());
        standbyList.push(new TestPassenger(PassengerStatus.REV, 0));
        standbyList.push(new TestPassenger(PassengerStatus.S0, 0));
        standbyList.push(new TestPassenger(PassengerStatus.REV, 1));
        standbyList.push(new TestPassenger(PassengerStatus.REV, 2));
        standbyList.push(new TestPassenger(PassengerStatus.S1, 0));
        standbyList.push(new TestPassenger(PassengerStatus.S0, 1));
        standbyList.push(new TestPassenger(PassengerStatus.S3, 0));
        standbyList.push(new TestPassenger(PassengerStatus.S4, 0));

        pass = standbyList.pop();
        assertEquals(PassengerStatus.REV, pass.status);
        assertEquals(0, pass.id);

        pass = standbyList.pop();
        assertEquals(PassengerStatus.REV, pass.status);
        assertEquals(1, pass.id);

        pass = standbyList.pop();
        assertEquals(PassengerStatus.REV, pass.status);
        assertEquals(2, pass.id);

        pass = standbyList.pop();
        assertEquals(PassengerStatus.S0, pass.status);
        assertEquals(0, pass.id);

        pass = standbyList.pop();
        assertEquals(PassengerStatus.S0, pass.status);
        assertEquals(1, pass.id);

        pass = standbyList.pop();
        assertEquals(PassengerStatus.S1, pass.status);
        assertEquals(0, pass.id);

        pass = standbyList.pop();
        assertEquals(PassengerStatus.S3, pass.status);
        assertEquals(0, pass.id);

        pass = standbyList.pop();
        assertEquals(PassengerStatus.S4, pass.status);
        assertEquals(0, pass.id);
    }

}
