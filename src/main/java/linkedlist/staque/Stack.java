/*
 * File: Stack.java
 * Description: A Stack data structure
 * based on the AbstractStaque data structure.
 * Authors:
 *   - Ilya Tsivilskiy
 * Copyright: (c) 2024 Ilya Tsivilskiy
 * License: This file is licensed under the MIT License.
 */
package linkedlist.staque;

import linkedlist.LLItem;

/**
 * A linked-ist based Stack-like collection
 * @param <T> A type parameter of the item values
 */
public class Stack<T> extends AbstractStaque<T> {
    /**
     * An empty Stack
     */
    public Stack() {
        super();
    }

    public Stack(T[] values) {
        super(values);
    }

    //region Public Overrides

    /**
     * Add the item to the top (beginning) of stack
     * @param item An element to add to
     */
    @Override
    public void add(T item) {
        this.push(item);
    }
    //endregion Public Overrides

    /**
     * Adds an item to the top of the stack
     * @param value A value to add to
     */
    private void push(T value) {
        LLItem<T> old1stItem = this.first;
        this.first = new LLItem<T>(value, old1stItem);
        this.size++;
    }
}
