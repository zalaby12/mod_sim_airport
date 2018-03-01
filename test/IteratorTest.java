import edu.cx4230.simulator.structs.List;
import edu.cx4230.simulator.structs.SinglyLinkedList;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class IteratorTest {

    public IteratorTest() {
    }

    @Test
    public void testEmpty() {
        List<Integer> list = new SinglyLinkedList<>();
        assertFalse(list.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void throwIfNextOnEmpty() {
        List<Integer> list = new SinglyLinkedList<>();
        list.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwIfIterateBeyond() {
        List<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> iter = list.iterator();
        System.out.println("iter: " + iter.next());
        System.out.println("iter: " + iter.next());
        System.out.println("iter: " + iter.next());
    }

    @Test
    public void testStandardIteration() {
        List<Integer> list = new SinglyLinkedList<>();
        Integer[] items = { 12, 3, 4 };
        for (int i : items) {
            list.add(i);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("item: " + list.get(i));
        }
        Iterator<Integer> iter = list.iterator();
        int i = 0;
        for (Integer item : items) {
            assertTrue(iter.hasNext());
            assertEquals(item, iter.next());
            System.out.println("item: " + list.get(i++));
        }
        assertFalse(iter.hasNext());
    }
}
