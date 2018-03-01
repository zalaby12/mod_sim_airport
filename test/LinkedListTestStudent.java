import edu.cx4230.simulator.structs.List;
import edu.cx4230.simulator.structs.Node;
import edu.cx4230.simulator.structs.SinglyLinkedList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LinkedListTestStudent {

    private List<String> list;

    @Rule
    public Timeout timeout = Timeout.millis(200);

    @Before
    public void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Test
    public void testAddStrings() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.add(0, "0a"); //0a
        list.add(1, "1a"); //0a 1a
        list.add(2, "2a"); //0a 1a 2a
        list.add(3, "3a"); //0a 1a 2a 3a

        assertEquals(4, list.size());

        Node<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());
        assertNull(current.getNext());
    }

    @Test
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.add("0a");
        list.add("1a");
        list.add("2a");
        list.add("3a");
        list.add("4a");
        list.add("5a"); //5a 4a 3a 2a 1a 0a *** No add to front, just add now

        assertEquals(6, list.size());

        Node<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());
        current = current.getNext();
        assertNull(current);
    }

    @Test
    public void testRemoveStrings() {
        assertEquals(0, list.size());

        list.add(0, "0a");
        list.add(1, "1a");
        list.add(2, "2a");
        list.add(3, "3a");
        list.add(4, "4a");
        list.add(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("2a", list.remove(2)); //0a 1a 3a 4a 5a

        assertEquals(5, list.size());
        Node<String> current = list.getHead();
        assertEquals("0a", current.getData());
        current = current.getNext();
        assertEquals("1a", current.getData());
        current = current.getNext();
        assertEquals("3a", current.getData());
        current = current.getNext();
        assertEquals("4a", current.getData());
        current = current.getNext();
        assertEquals("5a", current.getData());
        current = current.getNext();
        assertNull(current);
    }

    @Test
    public void testGetGeneral() {
        list.add(0, "0a");
        list.add(1, "1a");
        list.add(2, "2a");
        list.add(3, "3a");
        list.add(4, "4a"); //0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test
    public void testToArray() {
        String[] expectedItems = new String[10];

        for (int x = 0; x < expectedItems.length; x++) {
            expectedItems[x] = "a" + x;
            list.add(expectedItems[x]);
        }

        Object[] array = list.toArray();
        assertArrayEquals(expectedItems, array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeFirstOccuranceNullItemPassed() {
        list.removeFirstOccurrence(null);
    }
}
