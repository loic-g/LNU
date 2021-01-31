package lg222sv_assign3.count_words;

import java.util.Iterator;
import java.util.Stack;

/*This implementation was inspired by the lecture available online*/
public class TreeWordSet implements WordSet {
    private BST root = null;
    private int sz;

    public TreeWordSet() {
    }

    @Override
    public void add(Word word) {
        if (root==null){
            root = new BST(word);
            sz++;
        } else {
            root.add(word);
        }
    }

    @Override
    public boolean contains(Word word) {
        return root.contains(word);//return true if it contains
    }

    @Override
    public int size() {
        return sz;
    }

    //TOFINISH
    @Override
    public Iterator<Word> iterator() {
        return new TreeIterator(root);
    }

    @Override
    public String toString() {
        String text ="TreeWordSet : { ";
        Iterator<Word> it = iterator();
        while(it.hasNext()){
            text += it.next().getWord()+" ";
        }
        text+="}";
        /* I Don't really know how I can do this one*/
        return text;
    }

    private class BST{
        private Word w;
        private BST left =null;
        private BST right = null;

        public BST(Word w){
            this.w = w;
        }

        private void print(){
            //String strr = "";
            if(left!=null){
                left.print();
            }
            System.out.print(" "+w);
            //strr+=" "+w;
            if(right!=null){
                right.print();
            }


        }
        private void add(Word theword){
            if(theword.compareTo(w)<0){
                if(left==null){
                    left = new BST(theword);
                    sz++;
                } else {
                    left.add(theword);
                }
            } else if(theword.compareTo(w)>0){
                if(right==null){
                    right = new BST(theword);
                    sz++;
                } else {
                    right.add(theword);
                }
            }
        }

        private boolean contains(Word aword){
            if(aword.compareTo(w)<0){
                if(left==null){
                    return false;
                } else {
                    return left.contains(aword);
                }
            } else if(aword.compareTo(w)>0){
                if(right==null){
                    return false;
                } else {
                    return right.contains(aword);
                }
            }
            return true;
        }


    }

    /* This part was inspired by this: http://stackoverflow.com/a/17959135*/
    private class TreeIterator implements Iterator
    {
        private Stack<BST> stackTree = new Stack<>();
        private BST current;

        private TreeIterator(BST root)
        {
            current = root;
        }

        @Override
        public boolean hasNext()
        {
            return (!stackTree.isEmpty() || current != null);
        }

        @Override
        public Word next()
        {
            while (current != null) {
                stackTree.push(current);
                current = current.left;
            }

            current = stackTree.pop();
            BST node = current;
            current = current.right;

            return node.w;
        }
    }
}

