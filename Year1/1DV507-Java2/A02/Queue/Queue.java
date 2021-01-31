package lg222sv_assign2.Queue;

import java.util.Iterator;

/**
 * Queue is an Interface to implement a LinkedQueue using the head-tail approach, it follows the FIFO
 * (First In First Out) structure.
 * @author Loic Galland
 */
public interface Queue {
    /**
     * Gets the current queue size, For example the current amount of enqueued elements.
     * @return The amount of elements stored in the Queue
     */
    public int size();// current queue size

    /**
     * Return whether or not the Queue is currently empty.
     *
     * @return True if the Queue is empty, otherwise False.
     */
    public boolean isEmpty();         // true if queue is empty

    /**
     * Adds an element at the end of the Queue.
     *
     * @param element is the element to add to the Queue.
     */
    public void enqueue(Object element);   // add element at end of queue

    /**
     * Remove the first element of the Queue and return this element.
     * This is due to the type of Data Structure used here: FIFO(First in First Out)
     *
     * @return the first element before removing it.
     */
    public Object dequeue();               // return and remove first element.

    /**
     * Return the first element of the Queue without removing it.
     * This is due to the type of Data Structure used here: FIFO(First in First Out)
     *
     * @return the first element of the Queue without removing it.
     */
    public Object first();                 // return (without removing) first element

    /**
     * Return the last element of the Queue without removing it.
     * This is due to the type of Data Structure used here: FIFO(First in First Out)
     *
     * @return the last element of the Queue without removing it.
     */
    public Object last();                  // return (without removing) last element

    /**
     * Returns a String representation of all element in the Queue.
     * Starting from the first element and going to the last.
     * This is due to the type of Data Structure used here: FIFO(First in First Out)
     * Between each element a space will be added.
     *
     * @return a String with all the element from the Queue.
     */
    public String toString();              // return a string representation of the queue content

    /**
     * Return an iterator element to the Queue.
     *
     * @return An element iterator for this Queue.
     */
    public Iterator<Object> iterator();    // element iterator
}
