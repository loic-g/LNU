package lg222sv_assign4.FIFO_Ex1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class LinkedQueueTest {
    private lg222sv_assign4.FIFO_Ex1.LinkedQueue queueInt;
    private lg222sv_assign4.FIFO_Ex1.LinkedQueue queueString;



    @BeforeEach
    void StartMethod(){
        queueInt = new LinkedQueue<Integer>();
        queueString = new LinkedQueue<String>();
    }



    @Test
    public void testsize(){
        queueInt.enqueue(1);
        assertEquals(1,queueInt.size());//Expecting a return of 1

        queueInt.enqueue(2);
        queueInt.enqueue(3);
        queueInt.enqueue(4);
        assertEquals(4,queueInt.size()); //Expecting a return of 4
    }

    @Test
    public void testisEmpty(){
        assertEquals(true,queueInt.isEmpty());

        queueInt.enqueue(new Object());
        assertEquals(false,queueInt.isEmpty());

        queueInt.dequeue();
        assertEquals(true,queueInt.isEmpty());
    }

    @Test
    public void testenqueue(){
        queueString.enqueue("Element 1");
        assertEquals("Element 1",queueString.first());

        queueString.enqueue("Element 2");
        queueString.enqueue("Element 3");
        queueString.enqueue("Element 4");
        assertEquals("Element 4",queueString.last());
        assertEquals("Element 1",queueString.first());
    }

    @Test
    public void testdequeue(){

        try{
            queueString.dequeue();
        }catch(RuntimeException e){
            assertTrue(true);
        }
        queueString.enqueue("Hello");
        Object ob1kenoby = queueString.dequeue();
        assertEquals(ob1kenoby,"Hello");

        queueString.enqueue("It's");
        queueString.enqueue("Me!!!");
        queueString.enqueue("Java");
        Object ob2kenoby = queueString.dequeue();
        Object ob3kenoby = queueString.dequeue();
        assertEquals(ob2kenoby,"It's");
        assertEquals(ob3kenoby,"Me!!!");

        queueString.dequeue();
        assertEquals(true,queueString.isEmpty());
    }

    @Test
    public void testfirst(){
        try{
            queueInt.first();
        }catch (RuntimeException e){
            assertTrue(true);
        }
        queueInt.enqueue(1);
        queueInt.enqueue(2);
        queueInt.enqueue(3);
        queueInt.enqueue(4);
        assertEquals(1,queueInt.first());
        queueInt.dequeue();
        queueInt.dequeue();
        assertEquals(3,queueInt.first());
    }

    @Test
    public void testlast(){
        try{
            queueInt.last();
        }catch(RuntimeException e){
            assertTrue(true);
        }
        queueInt.enqueue(1);
        queueInt.enqueue(2);
        assertEquals(2,queueInt.last()); //Check if the last is equals to "2"

        queueInt.enqueue(3);
        queueInt.enqueue(4);
        queueInt.enqueue(5);
        assertEquals(5,queueInt.last());

        queueInt.dequeue();
        assertEquals(5,queueInt.last());
    }

    @Test
    public void testtoString(){
        queueString.enqueue("element 1");
        queueString.enqueue("element 2");
        queueString.enqueue("element 3");
        String str = "The Queue = element 1  element 2  element 3  ";
        assertEquals(str,queueString.toString());
    }
    @Test
    public void testIterator(){
        Iterator<Object> it = queueString.iterator();

        assertEquals(false,it.hasNext());

        boolean check=false;
        try{
            it.next();
        }catch (NullPointerException e){
            assertTrue(true);
        }

        queueString.enqueue("First Element");
        it = queueString.iterator();
        assertTrue(it.hasNext());
        assertEquals("First Element",it.next());
        queueString.enqueue("Second Element");
        queueString.enqueue("Third Element");
        queueString.enqueue("Fourth Element");
        it = queueString.iterator();
        it.next();
        assertEquals("Second Element",it.next());
        assertEquals("Third Element",it.next());
        assertEquals("Fourth Element",it.next());
    }

}