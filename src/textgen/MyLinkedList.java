package textgen;

import java.util.AbstractList;


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
	public boolean add(E element) {
		add(size, element);
		return true;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param index index where the element should be added
	 * @param element The element to add
	 * @throws NullPointerException if the element is null
	 * @throws IndexOutOfBoundsException if the index isn't between 0 & LinkedList max
	 */
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		} else if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		LLNode<E> n = new LLNode<>(element);
		LLNode<E> prevNode = head;

		for (int i = 0; i < index; i++) {
			prevNode = prevNode.next;
		}

		n.next = prevNode.next;
		n.prev = prevNode;
		n.prev.next = n;
		n.next.prev = n;
		size++;
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

	/** Return the size of the list */
	public int size() {
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		if (size == 0 || index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		LLNode<E> node;
		node = head;

		for (int i = 0; i <= index; i++) {
			node = node.next;
		}

		node.prev.next = node.next;
		node.next.prev = node.prev;
		E data = node.data;
		node = null;
		size--;

		return data;
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
