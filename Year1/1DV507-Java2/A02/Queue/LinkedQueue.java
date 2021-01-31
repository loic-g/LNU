package lg222sv_assign2.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedQueue implements the interface Queue.
 * @see lg222sv_assign2.Queue.Queue
 * @author Loic Galland
 *
 */
public class LinkedQueue implements Queue {
    private int size;
    private Node head;
    private Node tail;

    public LinkedQueue() {
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * Return the current size of the Queue.
     *
     * @return current size of the LinkedQueue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks whether or not the Queue is empty, if it is
     * it returns true, otherwise it returns false.
     *
     * @return true if the Queue is empty, otherwise false.
     */
    @Override
    public boolean isEmpty() {
        if (head == null)
            return true;
        else
            return false;

    }

    /**
     *
     *  Add an element at the end of the Queue
     * @param element is the element to add to the Queue.
     */
    @Override
    public void enqueue(Object element) {
        if (head == null) {
            head = new Node(element);
            tail = head;
            size++;
        } else {
            tail.next = new Node(element);
            tail = tail.next;
            size++;
        }
    }

    // return and remove first element.

    /**
     * Return and remove the first element of the Queue.
     * @return first element of the Queue
     */
    @Override
    public Object dequeue() {
        Object help = head.value;
        if (head == null) {
            tail = null;
            throw new NullPointerException("Can't dequeue if there is no element");
        } else {
            head = head.next;
            size--;
        }
        return help;
    }

    /**
     * Return the first element without removing it.
     *
     * @return the first element of the Queue
     */
    @Override
    public Object first() {
        if (isEmpty()) {
            throw new NoSuchElementException("There is no element in the Queue");
        } else
            return head.value;
    }
    /**
     * Return the last element without removing it.
     *
     * @return the last element of the Queue
     */
    @Override
    public Object last() {
        if (isEmpty()) {
            throw new NoSuchElementException("There is no element in the Queue");
        } else
            return tail.value;
    }
    /**
     * Return an iterator element to the Queue.
     *
     * @return An element iterator for this Queue.
     */
    @Override
    public Iterator<Object> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Returns a String representation of all element in the Queue.
     * Starting from the first element and going to the last.
     * This is due to the type of Data Structure used here: FIFO(First in First Out)
     * Between each element a space will be added.
     *
     * @return a String with all the element from the Queue.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("The Queue = ");
        Iterator<Object> it = this.iterator();
        while(it.hasNext()) {
            sb.append(it.next()+"  ");
        }
        return sb.toString();
    }

    /**
     * Private class that make the iterator works with the class Node.
     */
    private class LinkedListIterator implements Iterator<Object> {

        private Node current = head;

        @Override
        public boolean hasNext() {
            if (current != null)
                return true;
            else
                return false;
        }

        @Override
        public Object next() {
            Object element = current.value;
            current = current.next;
            return  element;

        }

    }

}
