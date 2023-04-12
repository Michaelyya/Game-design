package Assignment2;

import java.util.Iterator;

public abstract class MyLinkedList<E> implements MyList<E>{
    protected int size;

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }

    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public class DNode {
        private E element;
        private DNode next;
        private DNode prev;

        public DNode(E element) {
            this.element = element;
        }
    }
}
