package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// Sentinels
		head = new LLNode<>();
		tail = new LLNode<>();
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element)
	{
		if (element == null) {
			throw new NullPointerException();
		}

		LLNode<E> n = new LLNode<>(element);
		n.next = tail;
		n.prev = tail.prev;
		n.prev.next = n;
		n.next.prev = n;
		size++;

		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		if (size == 0 || index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}

		LLNode<E> currNode = head;

		for (int i = 0; i <= index; i++) {
			currNode = currNode.next;
		}

		return currNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param index index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element)
	{
		// TODO: Implement this method
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return -1;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		return null;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode() {
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}
}
