package textgen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;

	/** Run before each @Test method */
	@Before
	public void setUp() {
		emptyList  =  new MyLinkedList<Integer>();
		shortList  =  new MyLinkedList<String>();
		list1      =  new MyLinkedList<Integer>();
		longerList =  new MyLinkedList<Integer>();

		shortList.add("A");
		shortList.add("B");
		list1.add(65);
		list1.add(21);
		list1.add(42);

		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void emptyListGet() {
		emptyList.get(0);
		emptyList.get(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shortListGetOutBounds() {
		shortList.get(-1);
		shortList.get(2);
	}

	@Test
	public void shortListGet() {
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void longerListGet() {
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertEquals("Check " + i + " element", (Integer)i, longerList.get(i));
		}

		longerList.get(-1);
		longerList.get(LONG_LIST_LENGTH);
	}

	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test(expected = IndexOutOfBoundsException.class)
	public void list1RemoveOutBounds() {
		list1.remove(42);
		list1.remove(-2);
	}

	@Test
	public void list1Remove() {
		assertEquals("Remove: check a is correct ", (Integer)65, list1.remove(0));
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
	}

	@Test(expected = NullPointerException.class)
	public void shortListAdd() {
		shortList.add(null);
		assertEquals("Adding 'null' shouldn't change LinkedList", 2, shortList.size());
		assertEquals("Index 1 should equal to the value 'B'", "B", shortList.get(1));
		shortList.add("Plop");
		assertEquals("Adding one object", 3, shortList.size());
		assertEquals("Index 2 should equal to the value 'Plop'", "Plop", shortList.get(2));
	}

	@Test(expected = NullPointerException.class)
	public void shortListAddAtIndexNullObj() {
		shortList.add(1, null);
		shortList.add(42, "X");
		shortList.add(-21, "Z");
		assertEquals("shortList size is 0", 2, shortList.size());
		shortList.add(0, "Meh");
		assertEquals("shortList size is 3", 3, shortList.size());
		assertEquals("index 0 of shortList is 'Meh'", "Meh", shortList.get(0));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shortListAddAtIndexOutBounds() {
		shortList.add(42, "X");
		shortList.add(-21, "Z");
		assertEquals("shortList size is 0", 2, shortList.size());
	}

	@Test
	public void shortListAddAtIndex() {
		shortList.add(0, "Meh");
		assertEquals("shortList size is 3", 3, shortList.size());
		assertEquals("index 0 of shortList is 'Meh'", "Meh", shortList.get(0));
	}

	@Test
	public void testSize() {
		assertEquals("emptyList.size = 0", (Integer)0, (Integer)emptyList.size());
		assertEquals("shortList.size = 2", (Integer)2, (Integer)shortList.size());
		assertEquals("list1.size = 3", (Integer)3, (Integer)list1.size());
		assertEquals("longerList.size = 3", (Integer)LONG_LIST_LENGTH, (Integer)longerList.size());
		// Size after add
		// Size after remove
	}

	@Test(expected = NullPointerException.class)
	public void list1SetNullObj() {
		list1.set(0,  null);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void list1SetOutBounds() {
		list1.set(-2, -2);
		list1.set(55,  55);
	}

	@Test
	public void list1Set() {
		assertEquals("TEST: list1[0] = 65", (Integer)65, list1.get(0));
		list1.set(0, 42);
		assertEquals("TEST: list1[0] = 42", (Integer)42, list1.get(0));
	}

	@Test
	public void testToString() {
		assertTrue("TEST: shortList.toString", emptyList.toString().equals(""));
//		assertEquals("TEST: emptyList.toString = \"\"", "", emptyList.toString());
//		assertEquals("TEST: shortList.toString", "A, B", shortList.toString());
//		assertEquals("TEST: list1.toString", "65, 21, 42", list1.toString());
//		assertEquals("TEST: longerList.toString", "0, 1, 2, 3, 4, 5, 6, 7, 8, 9", longerList.toString());
	}
	
}
