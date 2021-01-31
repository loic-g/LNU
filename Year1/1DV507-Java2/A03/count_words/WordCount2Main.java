package lg222sv_assign3.count_words;

import java.util.Iterator;

public class WordCount2Main {
        public static void main (String[] args){

            HashWordSet hash = new HashWordSet();
            TreeWordSet tree = new TreeWordSet();

            Word word1 = new Word("Alo");
            Word word2 = new Word("Beverage");
            Word word3 = new Word("Crank");
            Word word4 = new Word("Destroy");
            Word word5 = new Word("Elephant");
            Word word6 = new Word("Flower");
            Word word7 = new Word("Get");
            Word word8 = new Word("House");
            Word word9 = new Word("Initiate");

            word1.hashCode();
            word2.hashCode();
            word3.hashCode();
            word4.hashCode();
            word5.hashCode();
            word6.hashCode();
            word7.hashCode();
            word8.hashCode();
            word9.hashCode();

            System.out.println("------------HashWordSet Test------------");
            hash.add(word1);
            hash.add(word2);
            hash.add(word3);
            hash.add(word4);
            hash.add(word5);
            hash.add(word6);
            hash.add(word7);
            hash.add(word8);
            hash.add(word9);
            //hash1.size();
            System.out.println("Contains : "+hash.contains(word9));
            //System.out.println("Iterator : "+hash1.iterator());
            System.out.println("Size HashSet : "+hash.size());
            System.out.println(hash.toString());
            //System.out.println(hash1.toString());

            System.out.println("------------TreeWordSet Test------------");
            tree.add(word1);
            tree.add(word2);
            tree.add(word3);
            System.out.println("Contains : "+tree.contains(word1));
            System.out.println("Size TreeSet : "+tree.size());

            System.out.println(tree.toString());





        }
    }


