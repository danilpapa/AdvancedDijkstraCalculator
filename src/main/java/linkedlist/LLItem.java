/*
 * File: LLItem.java
 * Description: An element of a linked list.
 * Authors:
 *   - Ilya Tsivilskiy
 * License: This file is licensed under the MIT License.
 */
package linkedlist;

/**
 * An item (element) of the Linked-List-based collection
 * @param <T> The type parameter of the item.value
 */
public class LLItem<T> {
    public T value;         // a value of this item
    public LLItem<T> next;    // a pointer to the next item in a collection

    /**
     * Make an empty item
     * that is connected with nothing
     */
    public LLItem() {
        this.value = null;
        this.next = null;
    }

    /**
     * Make an item
     * holding a specific value and pointing to the specific Node
     * @param value  An value this node holds
     * @param next  Another node this node points to
     */
    public LLItem(T value, LLItem<T> next) {
        this.value = value;
        this.next = next;
    }
}
