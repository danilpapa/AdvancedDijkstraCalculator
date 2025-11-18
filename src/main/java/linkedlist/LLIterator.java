/*
 * File: LLIterator.java
 * Description: An iterator
 * over the elements of a linked list,
 * enabling the use of an enhanced for loop.
 * Authors:
 *   - Ilya Tsivilskiy
 * Copyright: (c) 2024 Ilya Tsivilskiy
 */
package linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator over the Linked-List-based collection.
 * @param <T> The type parameter
 */
public class LLIterator<T> implements Iterator<T> {
    private LLItem<T> current;  // current item of the linked list

    /**
     * Creates an iterator over the linked list collection
     * @param first A pointer to the first item in a collection
     */
    public LLIterator(LLItem<T> first) {
        this.current = first;
    }

    /**
     * Check whether this collection has a next item
     * @return True, if it has th next item; false otherwise
     */
    public boolean hasNext() {
        return current != null;
    }

    /**
     * Return the next item in the linked list collection
     * @return Value of the next item
     */
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        T value = current.value;
        this.current = current.next;
        return value;
    }
}
