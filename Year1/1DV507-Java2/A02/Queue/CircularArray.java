package lg222sv_assign2.Queue;

import java.util.Iterator;

public class CircularArray implements Queue {
    private int size;
    private int first;
    private int last;

    private Object[] circulararray;

    public CircularArray(){
        size=0;
        first=-1;
        last=-1;
        circulararray=new Object[8];
    }
    @Override
    public int size() {return circulararray.length;}

    @Override
    public boolean isEmpty() {
        if (first==-1 && last==-1){
            return true;
        }else
            return false;
    }

    @Override
    public void enqueue(Object element) {
        if (this.isEmpty()){
            first++;
            last++;
            circulararray[first]=element;
        }else if(isFull()){
            resize();
            last++;
            circulararray[last]=element;
        }else{
            if (((last+1)%size())==0 || first!=0){
                last=0;
                circulararray[last]=element;
            }else {
                last++;
                circulararray[last]=element;
            }
        }
        size++;
    }

    @Override
    public Object dequeue() {
        if(isEmpty()) {
            throw new NullPointerException("The queue is empty, please enqueue some element before dequeue");
        }
        else if ((first + 1) == size()) {
                Object h = circulararray[first];
                first = -1;
                last=-1;
                size--;
                return h;
        }else{
            Object help = circulararray[first];
            circulararray[first]=null;
            first++;
            size--;
            return help;
        }
    }

    @Override
    public Object first() {
        if(this.isEmpty())
            throw new NullPointerException("The array is empty, please enqueue some element first");
        else
            return circulararray[first];
    }

    @Override
    public Object last() {
        if(this.isEmpty())
            throw new NullPointerException("The array is empty, please enqueue some element first");
        else
            return circulararray[last];
    }

    @Override
    public Iterator<Object> iterator() {return new CircularArrIterator();}

    private class CircularArrIterator implements Iterator<Object>{

        int check = first;
        Object element = circulararray[check];
        @Override
        public boolean hasNext() {
            if(element!=null)
                return true;
            else
                return false;
        }

        @Override
        public Object next() {
            Object help = element;
            element = circulararray[check];
            check = check + (1 % size());
            return help;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("The Queue: ");
        Iterator<Object> it = this.iterator();
        while (it.hasNext()){
            sb.append(it.next().toString() + " ");
        }
        return sb.toString();
    }

    private void resize(){
        //need to add new thing
        Object[] tmp = new Object[2 * circulararray.length];
        if(first==0){
            System.arraycopy(circulararray, 0, tmp, 0, circulararray.length);
        }else{
            for(int i=first;i<circulararray.length;i++){
                tmp[i]=circulararray[i];
            }
            for(int j=0;j<=last;j++)
                tmp[j]=circulararray[j];
        }
        circulararray = tmp;
    }

    private boolean isFull(){
        if(first == (last%this.size())+1)
            return true;
        else
            return false;
    }
}
