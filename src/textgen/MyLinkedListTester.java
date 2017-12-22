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
	
	/**
	 * Run before each @Test method
	 * @throws java.lang.Exception
	 */
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

	@Test
	public void testGet() {
		// Empty list
		try {
			emptyList.get(0);
		} catch (IndexOutOfBoundsException e) {}

		// Short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
		} catch (IndexOutOfBoundsException e) {}

		try {
			shortList.get(2);
		} catch (IndexOutOfBoundsException e) {}

		// Longer list contents
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertEquals("Check " + i + " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
		} catch (IndexOutOfBoundsException e) {}

		try {
			longerList.get(LONG_LIST_LENGTH);
		} catch (IndexOutOfBoundsException e) {}
	}

	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove() {
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		list1.remove(42);
	}

	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test(expected = NullPointerException.class)
	public void testAddEnd() {
		shortList.add(null);
		assertEquals("Adding 'null' shouldn't change LinkedList", 2, shortList.size());
		assertEquals("Index 1 should equal to the value 'B'", "B", shortList.get(1));
		shortList.add("Plop");
		assertEquals("Adding one object", 3, shortList.size());
		assertEquals("Index 2 should equal to the value 'Plop'", "Plop", shortList.get(2));
	}

	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex() {
		try {
			shortList.add(1, null);
			fail();
		} catch (NullPointerException e) {}

		try {
			shortList.add(42, "X");
			fail();
		} catch (IndexOutOfBoundsException e) {}

		try {
			shortList.add(-21, "Z");
			fail();
		} catch (IndexOutOfBoundsException e) {}

		assertEquals("shortList size is 0", 2, shortList.size());
		shortList.add(0, "Meh");
		assertEquals("shortList size is 3", 3, shortList.size());
		assertEquals("index 0 of shortList is 'Meh'", "Meh", shortList.get(0));
	}

	/** Test the size of the list */
	@Test
	public void testSize() {
		// Initial size
		assertEquals("emptyList.size = 0", (Integer)0, (Integer)emptyList.size());
		assertEquals("shortList.size = 2", (Integer)2, (Integer)shortList.size());
		assertEquals("list1.size = 3", (Integer)3, (Integer)list1.size());
		assertEquals("longerList.size = 3", (Integer)LONG_LIST_LENGTH, (Integer)longerList.size());
		// Size after add
		// Size after remove
	}

	/** Test setting an element in the list */
	@Test
	public void testSet() {
		try {
			list1.set(0,  null);
			fail();
		} catch (NullPointerException e) {}

		try {
			list1.set(-2,  -2);
			fail();
		} catch (IndexOutOfBoundsException e) {}

		try {
			list1.set(55,  55);
			fail();
		} catch (IndexOutOfBoundsException e) {}

		assertEquals("list1[0] = 65", (Integer)65, list1.get(0));
		list1.set(0, 42);
		assertEquals("list1[0] = 42", (Integer)42, list1.get(0));
		assertEquals("list1[1] = 21", (Integer)21, list1.get(1));
	}

	@Test
	public void testToString() {
		assertEquals("emptyList.toString", "", emptyList.toString());
		assertEquals("shortList.toString", "A\nB", shortList.toString());
		assertEquals("list1.toString", "65\n21\n42", list1.toString());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			if (i != LONG_LIST_LENGTH - 1)
				sb.append(i).append("\n");
			else
				sb.append(i);
		}
		String listStr = sb.toString();
		assertEquals("longerList.toString", listStr, longerList.toString());
	}
	
}
