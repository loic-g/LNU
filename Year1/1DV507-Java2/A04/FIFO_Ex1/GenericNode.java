package lg222sv_assign4.FIFO_Ex1;

public class GenericNode<T> {
    T val;
    GenericNode<T> next;

    public GenericNode(T element){
        this.val = element;
    }
}
