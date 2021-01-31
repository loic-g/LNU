package lg222sv_assign4.binheap;

public class BinaryIntHeapMain {
    public static void main(String [] args) {

        BinaryIntHeap binaryheap = new BinaryIntHeap();

        System.out.println(binaryheap.isEmpty());
        binaryheap.insert(5);
        binaryheap.insert(7);
        binaryheap.insert(99);
        binaryheap.insert(102);

        System.out.println(binaryheap);

        binaryheap.insert(12);
        binaryheap.insert(75);
        binaryheap.insert(15);
        binaryheap.insert(48);
        binaryheap.insert(600);
        binaryheap.insert(-27);
        binaryheap.insert(16);
        binaryheap.insert(7);
        binaryheap.insert(72);
        binaryheap.insert(450);
        binaryheap.insert(712);
        binaryheap.insert(2000);
        binaryheap.insert(-200);
        binaryheap.insert(101);
        binaryheap.insert(508);
        binaryheap.insert(72105);

        System.out.println("The Heap: " + binaryheap);
        System.out.println("Size of the binary heap: " + binaryheap.size());
        System.out.println("Is it empty? " + binaryheap.isEmpty());
        System.out.println(binaryheap.pullHighOne());
        System.out.println(binaryheap);
        System.out.println();

    }
}
