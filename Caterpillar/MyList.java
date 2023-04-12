package Assignment2;

import java.util.Iterator;

public interface MyList<E> extends Iterable<E> {
    int getSize();
    boolean isEmpty();
    void add(E element);
    void clear();
    E remove();
    Iterator<E> iterator();

    }

