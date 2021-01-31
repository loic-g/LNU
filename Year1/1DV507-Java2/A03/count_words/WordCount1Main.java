package lg222sv_assign3.count_words;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WordCount1Main {
    public static void main(String[] args){
        ArrayList<Word> tryarray = new ArrayList<>();
        Set<Word> hashset = new HashSet<>();
        Set<Word> treeset = new TreeSet<>();

        try {
            Scanner sc = new Scanner(new File("C:\\Users\\Loic PC\\Documents\\IntelliJ_java_Course\\1DV507\\src\\lg222sv_assign3\\count_words\\words.txt"));
            while(sc.hasNext()) {
                String help = sc.next();
                tryarray.add(new Word(help));
            }
            for(Word w:tryarray){
                hashset.add(w);
                treeset.add(w);
            }

        }catch(IOException ioe){
            System.out.println("NO WRONG PATH");
        }


        System.out.println("HashSet size: "+hashset.size());
        System.out.println("TreeSet size: "+treeset.size());
    }
}

