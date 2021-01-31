package lg222sv_assign3.count_words;

import java.util.Iterator;

/*This implementation was inspired by the lecture available online*/
public class HashWordSet implements WordSet {
    private int size;
    private Node[] bucket = new Node[8];

    public HashWordSet() {
    }

    //Add word if not already added
    @Override
    public void add(Word w) {
        int pos = getBucketNumber(w);
        Node node  = bucket[pos];//

        //search in the list
        while(node!=null){
            if(node.equals(w)){
                return; //return nothing if the element is found
            } else {
                node = node.next; // Goes to the next node in the list
            }
        }

        //Add the word as a new node
        node = new Node(w);
        node.next =bucket[pos];
        bucket[pos]=node;
        size++;//increase size when added

        if(size==bucket.length){
            rehash();
        }

    }

    //Return true if word contained
    @Override
    public boolean contains(Word word) {
        int position = getBucketNumber(word);
        Node node = bucket[position];

        //Search for the element
        while(node!=null){
            if(node.value.equals(word)){
                return true; // return true if the element is found
            } else {
                node = node.next; // otherwise it goes to the next one (it keeps searching)
            }
        }
        return false; // Not found
    }

    @Override
    public int size() {
        return size;
    }

    //TOFINISH
    @Override
    public Iterator<Word> iterator() {
        return new IteratorHashSet();
    }

    @Override
    public String toString() {
        String mess = "HashSet: { ";
        for(int i=0;i<bucket.length;i++){
            Node node1 = bucket[i];
            while(node1!=null){
                mess+= node1.value + " ";
                node1 = node1.next;
            }
        }
        mess+="}";
        return mess;
    }




    private void rehash(){
        Node[] tempo = bucket;//Copy old bucket.
        bucket = new Node[2*tempo.length];//Creates a new one with a double size from the original
        size= 0; //reset size to 0
        //We insert old value in the new bucket
        for(Node n : tempo){
            if(n==null){
                continue; //empty
            }
            while (n!=null){
                add(n.value); //we add element
                n=n.next;
            }
        }
    }

    //Private method that get the bucket number of a word
    private int getBucketNumber(Word w){
        int hc = w.hashCode();//Take HashCode from Word class
        if(hc<0){
            hc=Math.abs(-hc); //Make sure it's non negative
        }
        return hc%bucket.length; //simple hash function
    }

    //Private class that create the nodes
    private class Node{
        Word value;
        Node next = null;
        public Node(Word w){value = w;}
        public String toString(){return value.toString();}
    }

    private class IteratorHashSet implements Iterator<Word>{
        private int pos;
        private Node current;

        public IteratorHashSet(){
            pos = -1;
            current=null;
        }

        @Override
        public boolean hasNext() {
            if (current!=null && current.next !=null)
                return true;

            for(int i =pos+1;i<bucket.length;i++){
                if(bucket[i]!=null)
                    return true;
            }
            return false;
        }

        @Override
        public Word next() {
            if(current == null || current.next==null){
                if(current!= null)
                    pos++;

                if (pos <bucket.length)
                    current = bucket[pos];
                else
                    throw new IndexOutOfBoundsException();
            }else
                current = current.next;

            return current.value;
        }
    }

}
