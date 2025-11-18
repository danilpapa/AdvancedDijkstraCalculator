/*
 * File: IStaque.java
 * Description:
 * Authors:
 *   - Ilya Tsivilskiy
 * License: This file is licensed under the MIT License.
 */
package linkedlist.staque;

/**
 * An interface for Stack and Queue data structures.
 * @param <T> The type parameter
 */
public interface IStaque<T> extends Iterable<T> {
    /**
     * Add an element to the collection
     * @param element An element to add to
     */
    void add(T element);

    /**
     * Retrieve an element and remove it from the collection
     * @return Retrieved element
     */
    T extract();

    /**
     * Retrieve/get an element from the collection without removing it
     * @return Retrieved element
     */
    T get();

    /**
     * Check whether the collection has elements
     * @return Returns true if the collection is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Get the number of elements in the collection
     * @return The number of elements
     */
    int getSize();

    /**
     * Perform a sequence of actions on current collection
     * @param actions ADD, GET or EXTRACT
     */
    public void doActions(StaqueAction<T>[] actions, boolean debug);
}
