/*
 * File: AbstractStaque.java
 * Description: A base class
 * for linked-list-based collections
 * implementing shared functionality
 * between Stack and Queue data structures.
 * Authors:
 *   - Ilya Tsivilskiy
 * Copyright: (c) 2024 Ilya Tsivilskiy
 * License: This file is licensed under the MIT License.
 */
package linkedlist.staque;

import linkedlist.LLItem;
import linkedlist.LLIterator;
import utils.Logger;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An abstract class implementing the shared interface for Stack and Queue data structures.
 * @param <T> The type parameter
 */
public abstract class AbstractStaque<T>
        implements IStaque<T>, Iterable<T> {
    protected LLItem<T> first;    // a pointer to the beginning of the list
    protected LLItem<T> last;     // a pinter to the end of the list
    protected int size;           // size of the list (number of its items)

    //region Public methods
    /**
     * Default constructor for a linked-list-based Staque collection
     */
    public AbstractStaque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Make a linked-list-based Staque collection
     * from an array of item values
     * @param values An array of values
     */
    public AbstractStaque(T[] values) {
        for (T value : values) {
            this.add(value);
        }
    }

    /**
     * Get an element that is the most recently added to the collection
     * @return A value of the most recently added element
     * @throws NoSuchElementException if the collection is empty
     */
    @Override
    public T get() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("No such element in the collection!");
        }
        return this.first.value;
    }

    /**
     * Get an element that is the most recently added,
     * and removes it from the collection
     * @return A value of the most recently added element
     */
    @Override
    public T extract() {
        T old1stValue = this.get();
        this.first = this.first.next;   // 'delete' the first node by making it the next one
        this.size--;
        return old1stValue;
    }

    /**
     * An iterator to traverse the elements of the linked list
     * @return Iterator
     */
    public Iterator<T> iterator() {
        return new LLIterator<>(this.first);
    }

    /**
     * Makes a string representation of the list collection
     * @return A string containing values of all items
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }

    @Override
    public void doActions(StaqueAction<T>[] actions, boolean debug) {
        for (StaqueAction<T> act : actions) {
            switch (act.type) {
                case ADD:
                    this.add(act.value);
                    if (debug) {
                        Logger.write("Added:", act.value, "\n");
                    }
                    break;
                case GET:
                    T takenValue = this.get();
                    if (debug) {
                        Logger.write("Got:", takenValue, "\n");
                    }
                    break;
                case EXTRACT:
                    T retrievedValue = this.extract();
                    if (debug) {
                        Logger.write("Extracted:", retrievedValue, "\n");
                    }
                    break;
            }
        }
    }
    //endregion Public methods

    //region Interface overrides
    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public int getSize() {
        return this.size;
    }
    //endregion Interface overrides
}