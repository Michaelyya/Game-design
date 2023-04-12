package Assignment2;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E> {
	private DNode head;
	private DNode tail;


	@Override
	public void add(E element) {
		addLast(element);
	}

	@Override
	public E remove() {
		return removeLast();
	}

	@Override
	public void clear() {
		while (!isEmpty()) {
			remove();
		}
	}

	public void addFirst(E element) {
		DNode node = new DNode(element);
		if (isEmpty()) {
			head = tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
		size++;
	}

	public void addLast(E element) {
		DNode node = new DNode(element);
		if (isEmpty()) {
			head = tail = node;
		}
		else{
			node.prev = tail;
			tail.next = node;
			tail = node;
		}
		size++;
	}

	public E removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E element = head.element;
		if (head == tail) {
			head = tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
		size--;
		return element;
	}

	public E removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E element = tail.element;
		if (tail == head) {
			tail = head = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}
		size--;
		return element;
	}

	public E peekFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E element = head.element;
		return element;
	}

	public E peekLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E element = tail.element;
		return element;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof MyDoublyLinkedList)) {
			return false;
		}
		if (!(((MyDoublyLinkedList) obj).size == this.size)) {
			return false;
		}
		MyDoublyLinkedList object = (MyDoublyLinkedList<E>) obj;
		DNode node2 = object.head;
		DNode node1 = this.head;
		if (object.size == this.size) {
			while (node1 != null){
				if(node1.element.equals(node2.element)){
					node1 = node1.next;
					node2 = node2.next;
				}else{
					return false;
				}
			}
		}
		return true;
	}

	public Iterator<E> iterator() {
		return new DLLIterator();
	}

	private class DNode {
		private E element;
		private DNode next;
		private DNode prev;

		private DNode(E element) {
			this.element = element;
		}
	}

	private class DLLIterator implements Iterator<E> {
		DNode curr;

		public DLLIterator() {
			this.curr = head;
		}

		public boolean hasNext() {
			return this.curr != null;
		}

		public E next() {
			if (!this.hasNext())
				throw new NoSuchElementException();

			E element = this.curr.element;
			this.curr = this.curr.next;
			return element;
		}
	}

}