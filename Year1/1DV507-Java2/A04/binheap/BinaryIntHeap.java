package lg222sv_assign4.binheap;

public class BinaryIntHeap {
    private static final int BASIC_CAPACITY = 100;
    private int [] element;
    private int ElementCounts;

    public BinaryIntHeap()  {
        ElementCounts = 0;
        element = new int[BASIC_CAPACITY];
    }

    public void insert(int n) {

        if(ElementCounts == element.length/2)
            expand();

        element[ElementCounts++] = n;

        int childPos = ElementCounts-1;
        int parentPos = (childPos-1)/2;

        while(element[parentPos] < element[childPos]) {
            swapping(parentPos,childPos);

            childPos = parentPos;
            parentPos = (childPos-1)/2;
        }

    }

    public int pullHighOne() {

        int highest = element[0];

        swapping(0,ElementCounts-1);

        element[ElementCounts-1] = 0;

        int parentPos = 0;
        int leftChild = 1;
        int rightChild = 2;

        while(element[leftChild] > element[parentPos] || element[rightChild] > element[parentPos]) {

            if(element[leftChild] > element[rightChild]) {
                swapping(parentPos,leftChild);
                parentPos = leftChild;

            }else {
                swapping(parentPos, rightChild);
                parentPos = rightChild;
            }

            leftChild = 2*parentPos+1;
            rightChild = 2*parentPos+2;

            if(leftChild > element.length || rightChild > element.length)
                break;
        }
        ElementCounts--;
        return highest;
    }

    public int size() {
        return ElementCounts;
    }
    public boolean isEmpty() {
        return ElementCounts == 0;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ElementCounts; i++) {
            sb.append(element[i] + " ");
        }
        return sb.toString();
    }

    private void swapping(int i, int j) {

        int help = element[i];
        element[i] = element[j];
        element[j] = help;
    }

    private void expand() {
        int [] help = new int[2*ElementCounts];
        System.arraycopy(element, 0, help, 0, element.length);
        element = help;
    }
}
