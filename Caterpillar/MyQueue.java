package Assignment2;

import java.util.Iterator;

public class MyQueue<E> {
    private MyDoublyLinkedList<E> myDoublyLinkedList;

    public MyQueue() {
        myDoublyLinkedList = new MyDoublyLinkedList<>();
    }

    public void enqueue(E element) {
        myDoublyLinkedList.addLast(element);
    }

    public E dequeue() {
        return myDoublyLinkedList.removeFirst();
    }

    public boolean isEmpty() {
        return myDoublyLinkedList.isEmpty();
    }

    public void clear() {
        myDoublyLinkedList.clear();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MyQueue<?>)) {
            return false;
        }
        return (((MyQueue)obj).myDoublyLinkedList).equals(this.myDoublyLinkedList);
    }
    public int getSize () {
            return myDoublyLinkedList.size;
    }
}
