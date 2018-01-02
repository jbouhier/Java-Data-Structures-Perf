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

	@Test
	public void testGet() {
		// Empty list bounds
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}

		// Short list, first contents, then bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}

		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}

		// Longer list contents
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertEquals("Check "+ i + " element", (Integer)i, longerList.get(i));
		}

		// Longer list bounds
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}

		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
	}

	@Test
	public void testSet() {
		// Test null and bounds
		try {
			list1.set(0,  null);
			fail("Check if 2nd param is null");
		} catch (NullPointerException e) {}

		try {
			list1.set(-1, 99);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {}

		try {
			list1.set(list1.size(), 99);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {}

		// Test valid change
		assertEquals("TEST: list1[0] = 65", (Integer)65, list1.get(0));
		list1.set(0, 42);
		assertEquals("TEST: list1[0] = 42", (Integer)42, list1.get(0));
	}

	@Test
	public void testRemove() {
		try {
			list1.remove(42);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {}

		try {
			list1.remove(-2);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {}

		assertEquals("Remove: check a is correct ", (Integer)65, list1.remove(0));
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
	}

	@Test
	public void testAdd() {
		try {
			shortList.add(null);
			fail("Check null paramater");
		} catch (NullPointerException e) {}

		try {
			shortList.add(1, null);
			fail("Check null paramater");
		} catch (NullPointerException e) {}

		try {
			shortList.add(42, "X");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {}

		try {
			shortList.add(-21, "Z");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {}

		assertEquals("Adding 'null' shouldn't change LinkedList", 2, shortList.size());
		assertEquals("Index 1 should equal to the value 'B'", "B", shortList.get(1));
		shortList.add("Plop");
		assertEquals("Adding one object", 3, shortList.size());
		assertEquals("Index 2 should equal to the value 'Plop'", "Plop", shortList.get(2));
	}

}
