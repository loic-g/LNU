package lg222sv_assign4.FIFO_Ex1;

import java.util.Iterator;

public class LinkedQueue<T> implements GenericQueue<T> {

    private GenericNode<T> head =null;
    private GenericNode<T> tail = null;
    private int sz = 0;

    @Override
    public int size() {
        return sz;
    }

    @Override
    public boolean isEmpty() {
        if (head==null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void enqueue(T element) {
        if (head==null){
            head=new GenericNode<T>(element);
            tail=head;
        }else{
            tail.next=new GenericNode<T>(element);
            tail=tail.next;
        }
        sz++;
    }

    @Override
    public T dequeue() {
       if(head==null){
           throw new RuntimeException("There is no element on the queue");
       }else{
           T a = head.val;
           head=head.next;
           sz--;
           return a;
       }
    }

    @Override
    public T first() {
        if(isEmpty())
            throw new RuntimeException("There is no element on the queue");
        else{
            return head.val;
        }
    }

    @Override
    public T last() {
        if(isEmpty()){
            throw new RuntimeException("There is no element on the queue");
        }else
            return tail.val;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<T>{

        private GenericNode<T> currentNode = head;

        @Override
        public boolean hasNext(){
            if(currentNode!=null){
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T next(){
            T element = currentNode.val;
            currentNode = currentNode.next;
            return element;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("The Queue = ");
        Iterator<T> it = this.iterator();
        while(it.hasNext()) {
            sb.append(it.next()+"  ");
        }
        return sb.toString();
    }

}
