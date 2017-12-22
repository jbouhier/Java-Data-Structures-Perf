package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

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
	public void setUp() throws Exception {
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();

		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}

		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
	}


	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet()
	{
		emptyList.get(0);

		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		shortList.get(-1);
		shortList.get(2);

		// test longer list contents
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertEquals("Check " + i + " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		longerList.get(-1);
		longerList.get(LONG_LIST_LENGTH);
	}
	

	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
//	@Test
//	public void testRemove()
//	{
//		int a = list1.remove(0);
//		assertEquals("Remove: check a is correct ", 65, a);
//		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
//		assertEquals("Remove: check size is correct ", 2, list1.size());
//
//		// TODO: Add more tests here
//	}

	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		try {
			shortList.add(null);
			fail();
		} catch (NullPointerException e) {}

		try {
			shortList.add(42, "X");
			fail();
		} catch (IndexOutOfBoundsException e) {}

	}

	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		// TODO: implement this test

	}

	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
	}





	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test

	}

	
	// TODO: Optionally add more test methods.
	
}
