/*
 * File: Queue.java
 * File: Stack.java
 * Description: A Queue data structure
 * based on the AbstractStaque data structure.
 * Authors:
 *   - Ilya Tsivilskiy
 * Copyright: (c) 2024 Ilya Tsivilskiy
 * License: This file is licensed under the MIT License.
 */
package linkedlist.staque;

import linkedlist.LLItem;

/**
 * A linked-ist based Queue-like collection
 * @param <T> A type parameter of the item values
 */
public class Queue<T> extends AbstractStaque<T> {
    /**
     * An empty queue
     */
    public Queue() {
        super();
    }

    /**
     * Adds an elements of given array to the queue
     * @param values 1D array of values
     */
    public Queue(T[] values) {
        super(values);
    }

    /**
     * Add an item to the end of queue
     * @param item An element to add to
     */
    @Override
    public void add(T item) {
        this.enqueue(item);
    }

    /**
     * Retrieve and remove the front item from the queue
     * @return Retrieved element
     */
    @Override
    public T extract() {
        return this.dequeue();
    }

    /**
     * Adds the element to the end of this queue
     * @param value A value to append
     */
    private void enqueue(T value) {
        LLItem<T> oldLastItem = this.last;
        this.last = new LLItem<T>(value, null);
        if (isEmpty()) {
            this.first = this.last;
        } else {
            oldLastItem.next = this.last;
        }
        size++;
    }

    /**
     * Removes and returns the value of the item
     * that was added recently (the 1st one)
     * @return The most recently added value
     */
    private T dequeue() {
        T value = super.extract();
        // nullify the hanging items
        if (isEmpty()) {
            this.last = null;
        }
        return value;
    }
}
