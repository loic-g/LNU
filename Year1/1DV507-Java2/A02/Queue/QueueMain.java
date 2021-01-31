package lg222sv_assign2.Queue;

public class QueueMain {
    public static void main(String[] args) {

        LinkedQueue lq = new LinkedQueue();
        lq.enqueue(1);
        lq.enqueue(2);
        lq.enqueue(3);
        lq.enqueue(4);
        System.out.println("First Element "+lq.first());
        System.out.println("Last Element "+lq.last());
        System.out.println("Object dequeue "+lq.dequeue());
        System.out.println("First Element "+lq.first());
        System.out.println("\n"
                + "Print out: ");
        System.out.println(lq.toString());

    }
}
