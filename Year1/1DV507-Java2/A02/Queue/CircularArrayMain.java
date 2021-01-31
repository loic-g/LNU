package lg222sv_assign2.Queue;

public class CircularArrayMain {
    public static void main(String[] args){

        CircularArray ca = new CircularArray();
        System.out.println("Is it Empty?"+ca.isEmpty());

        ca.enqueue("One");
        ca.enqueue("Two");
        ca.enqueue("Three");
        ca.enqueue("Four");
        System.out.println("Queue size: "+ca.size());
        System.out.println(ca.toString());
       // System.out.println("Object dequeue: "+ca.dequeue());
        //System.out.println("Object dequeue: "+ca.dequeue());
        ca.enqueue("Five");
        ca.enqueue("Six");
        ca.enqueue("Seven");
        ca.enqueue("Height");
        ca.enqueue("Nine");
        System.out.println("First element: "+ca.first());
        System.out.println("Last element: "+ca.last());
        System.out.println("Object dequeue: "+ca.dequeue());
        System.out.println("Object dequeue: "+ca.dequeue());
        System.out.println("Object dequeue: "+ca.dequeue());
        System.out.println("Object dequeue: "+ca.dequeue());
        System.out.println("Object dequeue: "+ca.dequeue());
        System.out.println("Object dequeue: "+ca.dequeue());
        ca.enqueue("Ten");
        System.out.println("First element: "+ca.first());
        ca.enqueue("Eleven");
        System.out.println("Last element: "+ca.last());



    }
}
