package lg222sv_assign2.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
/*
I couldn't see how to change the code so that both LinkedQueue and CircularQueue could be tested at the same place.
So it only included the Test for LinkedQueue.
 */
class LinkedQueueTest {
    private LinkedQueue queue;
    private CircularArray circularqueue;


    @BeforeEach
     void StartMethod(){
        queue = new LinkedQueue();
        circularqueue = new CircularArray();
    }



    @Test
    public void testsize(){
        queue.enqueue(new Object());
        assertEquals(1,queue.size());//Expecting a return of 1

        queue.enqueue(new Object());
        queue.enqueue(new Object());
        queue.enqueue(new Object());
        assertEquals(4,queue.size()); //Expecting a return of 4
    }

    @Test
    public void testisEmpty(){
        assertEquals(true,queue.isEmpty());

        queue.enqueue(new Object());
        assertEquals(false,queue.isEmpty());

        queue.dequeue();
        assertEquals(true,queue.isEmpty());
    }

    @Test
    public void testenqueue(){
        queue.enqueue("Element 1");
        assertEquals("Element 1",queue.first());

        queue.enqueue("Element 2");
        queue.enqueue("Element 3");
        queue.enqueue("Element 4");
        assertEquals("Element 4",queue.last());
        assertEquals("Element 1",queue.first());
    }

    @Test
    public void testdequeue(){

        try{
            queue.dequeue();
        }catch(NullPointerException e){
            assertTrue(true);
        }
        queue.enqueue("Hello");
        Object ob1kenoby = queue.dequeue();
        assertEquals(ob1kenoby,"Hello");

        queue.enqueue("It's");
        queue.enqueue("Me!!!");
        queue.enqueue("Java");
        Object ob2kenoby = queue.dequeue();
        Object ob3kenoby = queue.dequeue();
        assertEquals(ob2kenoby,"It's");
        assertEquals(ob3kenoby,"Me!!!");

        queue.dequeue();
        assertEquals(true,queue.isEmpty());
    }

    @Test
    public void testfirst(){
        try{
            queue.first();
        }catch (NoSuchElementException e){
           assertTrue(true);
        }
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        assertEquals(1,queue.first());
        queue.dequeue();
        queue.dequeue();
        assertEquals(3,queue.first());
    }

    @Test
    public void testlast(){
        try{
            queue.last();
        }catch(NoSuchElementException e){
            assertTrue(true);
        }
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2,queue.last()); //Check if the last is equals to "2"

        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        assertEquals(5,queue.last());

        queue.dequeue();
        assertEquals(5,queue.last());
    }

    @Test 
    public void testtoString(){
        queue.enqueue("element 1");
        queue.enqueue("element 2");
        queue.enqueue("element 3");
        String str = "The Queue = element 1  element 2  element 3  ";
        assertEquals(str,queue.toString());
    }

    @Test
    public void testIterator(){
        Iterator<Object> it = queue.iterator();

        assertEquals(false,it.hasNext());

        boolean check=false;
        try{
            it.next();
        }catch (NullPointerException e){
            assertTrue(true);
        }

        queue.enqueue("First Element");
        it = queue.iterator();
        assertTrue(it.hasNext());
        assertEquals("First Element",it.next());
        queue.enqueue("Second Element");
        queue.enqueue("Third Element");
        queue.enqueue("Fourth Element");
        it = queue.iterator();
        it.next();
        assertEquals("Second Element",it.next());
        assertEquals("Third Element",it.next());
        assertEquals("Fourth Element",it.next());
    }






}