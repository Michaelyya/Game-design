package Assignment2;
import java.util.Iterator;
public class MyStack<E> {
    private MyDoublyLinkedList<E> myDoublyLinkedList;

    public MyStack() {
        myDoublyLinkedList = new MyDoublyLinkedList<>();
    }

    public void push(E element) {
        myDoublyLinkedList.addFirst(element);
    }

    public E pop() {
        E element = myDoublyLinkedList.removeFirst();
        return element;
    }

    public E peek() {
        E element = myDoublyLinkedList.peekFirst();
        return element;
    }

    public boolean isEmpty() {
        return myDoublyLinkedList.isEmpty();
    }

    public void clear() {
        myDoublyLinkedList.clear();
    }

    public int getSize() {
        return myDoublyLinkedList.getSize();
    }
}