import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import edu.cx4230.simulator.structs.List;
import edu.cx4230.simulator.structs.SinglyLinkedList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class LinkedListTest {
    
    private List<String> stringList;
    private List<Integer> intList;
    
    @Rule
    public Timeout timeout = Timeout.millis(200);
    
    @Before
    public void setUp() throws Exception {
        stringList = new SinglyLinkedList<String>();
        intList = new SinglyLinkedList<Integer>();
    }
    
    @Test
    public void testAddAtIndex() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());
        
        stringList.add(0, "String0");
        assertEquals("String0", stringList.get(0));

        stringList.add(1, "String1");
        assertEquals("String1", stringList.get(1));

        stringList.add(2, "String2");
        assertEquals("String2", stringList.get(2));

        stringList.add(1, "String15");
        assertEquals("String15", stringList.get(1));

        int size = stringList.size();
        stringList.add(size - 1, "String" + (size - 1));
        assertEquals("String" + (size - 1), stringList.get(size - 1));
    }
    
    @Test
    public void testGet() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());
        
        stringList.add("String1");
        assertEquals("String1", stringList.get(0));
        
        for (int i = 0; i < 1000; i++) {
            stringList.add("String" + i);
            intList.add(i);
        }
        
        for (int i = 0; i < 1000; i+=4) {
            assertEquals("String" + i, stringList.get(i + 1));
            assertEquals((Integer) i, intList.get(i));
        }
        
        assertEquals("String999", stringList.get(999 + 1));
        assertEquals((Integer) 999, intList.get(999));
    }
    
    @Test
    public void testRemoveAtIndex() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());
        
        stringList.add("String1");
        assertEquals("String1", stringList.remove(0));
        
        for (int i = 0; i < 1000; i++) {
            stringList.add("String" + i);
            intList.add(i);
        }
        
        assertEquals("String499", stringList.remove(499));
        assertEquals("String399", stringList.remove(399));
        assertEquals("String599", stringList.remove(597));
        assertEquals((Integer) 499, intList.remove(499));
        assertEquals((Integer) 0, intList.remove(0));
        assertEquals((Integer) 999, intList.remove(997));
    }
    
    @Test
    public void testAddToFront() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        for (int i = 0; i < 1000; i++) {
            stringList.add("String" + i);
            assertEquals(i + 1, stringList.size());
            assertEquals("String" + 0, stringList.get(0));

            intList.add(i);
            assertEquals(i + 1, intList.size());
            assertEquals((Integer) 0, intList.get(0));
        }
    }
    
    @Test
    public void testadd() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        for (int i = 0; i < 1000; i++) {
            stringList.add("String" + i);
            assertEquals(i + 1, stringList.size());
            assertEquals("String" + i, stringList.get(i));

            intList.add(i);
            assertEquals(i + 1, intList.size());
            assertEquals((Integer) i, intList.get(i));
        }
    }
    
    @Test
    public void testRemoveFromFront() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        assertNull(stringList.remove());
        
        stringList.add("String1");
        assertEquals("String1", stringList.remove());
        
        for (int i = 0; i < 1000; i++) {
            stringList.add("String" + i);
        }
        
        intList.add(1);
        
        assertEquals("String0", stringList.remove());
        assertEquals((Integer) 1, intList.remove());
    }
    
    @Test
    public void testRemoveFromBack() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        assertNull(stringList.removeFromBack());

        stringList.add("String1");
        assertEquals("String1", stringList.removeFromBack());

        for (int i = 0; i < 1000; i++) {
            stringList.add("String" + i);
        }
        
        intList.add(1);
        
        assertEquals("String999", stringList.removeFromBack());
        assertEquals((Integer) 1, intList.removeFromBack());
    }
    
    @Test
    public void testRemoveFirstOccurrence() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());
        
        stringList.add("Hello");
        assertEquals(0, stringList.removeFirstOccurrence("Hello"));
        
        stringList.add("Hello");
        stringList.add("Hello2");
        stringList.add("Hello1");
        stringList.add("Hello");
        stringList.add("Hello3");
        stringList.add("Hello1");
        
        intList.add(0);
        intList.add(2);
        intList.add(3);
        intList.add(6);
        intList.add(3);
        intList.add(4);
        
        assertEquals(0, stringList.removeFirstOccurrence("Hello"));
        assertEquals(1, stringList.removeFirstOccurrence("Hello1"));
        
        assertEquals(2, intList.removeFirstOccurrence(3));
        assertEquals(4, intList.removeFirstOccurrence(4));

    }
    
    @Test
    public void testToArray() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());
        
        String[] stringItems = new String[10];
        Integer[] intItems = new Integer[0];

        for (int x = 0; x < stringItems.length; x++) {
            stringItems[x] = "a" + x;
            stringList.add(stringItems[x]);
        }

        assertArrayEquals(stringItems, stringList.toArray());
        assertArrayEquals(intItems, intList.toArray());
    }
    
    @Test
    public void testIsEmpty() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());

        assertTrue(stringList.isEmpty());
        
        for (int i = 0; i < 1000; i++) {
            stringList.add("String" + 1);
        }
        
        intList.add(1);
        
        assertFalse(intList.isEmpty());
        assertFalse(stringList.isEmpty());
    }
    
    @Test
    public void testSize() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());
        
        for (int i = 0; i < 1000; i++) {
            stringList.add("String" + i);
            intList.add(i);
        }
        
        for (int i = 0; i < 100; i++) {
            intList.removeFromBack();
        }

        assertEquals(1000, stringList.size());
        assertEquals(900, intList.size());

    }
    
    @Test
    public void testClear() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());
        
        for (int i = 0; i < 1000; i++) {
            stringList.add("String" + i);
        }

        stringList.clear();
        intList.clear();

        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());
    }
    
    @Test
    public void testGetHead() {
        assertEquals(0, stringList.size());
        assertEquals(0, intList.size());
        
        assertNull(stringList.getHead());
        
        for (int i = 0; i < 1000; i++) {
            stringList.add("String" + i);
            if (i == 0) {
                assertEquals("String0", stringList.getHead().getData());
            }
        }
        
        intList.add(1);
        
        assertEquals((Integer) 1, intList.getHead().getData());
        assertEquals("String0", stringList.getHead().getData());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void RemoveIllegalArgumentTest() {
        stringList.removeFirstOccurrence(null);
    }
        
    @Test(expected = IllegalArgumentException.class)
    public void AddIllegalArgumentTest() {
        stringList.add(0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void FrontIllegalArgumentTest() {
        stringList.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void BackIllegalArgumentTest() {
        stringList.add(null);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void addIndexOutOfBoundsTest() {
        assertEquals(0, stringList.size());
        stringList.add(1, "Hello");
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void addLowerIndexOutOfBoundsTest() {
        assertEquals(0, stringList.size());
        stringList.add(-1, "Hello");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBoundsTest() {
        assertEquals(0, stringList.size());
        stringList.get(0);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void getLowerIndexOutOfBoundsTest() {
        assertEquals(0, stringList.size());
        stringList.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexOutOfBoundsTest() {
        assertEquals(0, stringList.size());
        stringList.remove(0);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexOutOfLowerBoundsTest() {
        assertEquals(0, stringList.size());
        stringList.remove(-1);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void removeEmptyNoSuchElementTest() {
        assertEquals(0, stringList.size());
        stringList.removeFirstOccurrence("Hello");
    }
    
    @Test(expected = NoSuchElementException.class)
    public void removeNoSuchElementTest() {
        assertEquals(0, stringList.size());
        
        for (int i = 0; i < 1000; i++) {
            stringList.add("String" + i);
        }
        
        stringList.removeFirstOccurrence("Hello");
    }
}