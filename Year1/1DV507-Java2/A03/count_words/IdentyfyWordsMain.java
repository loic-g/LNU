package lg222sv_assign3.count_words;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class IdentyfyWordsMain {


    public static void main(String[] args){
        readFile("C:\\Users\\Loic PC\\Documents\\IntelliJ_java_Course\\1DV507\\src\\lg222sv_assign3\\count_words\\HistoryOfProgramming.txt");
    }

    public static void readFile(String Path){
        StringBuilder sb = new StringBuilder();
        try {
            Scanner sc = new Scanner(new File(Path));
            while(sc.hasNext()){
                String help = sc.nextLine();
                for(int i=0;i<help.length();i++){
                    char helpchar=help.charAt(i);
                    if(Character.isLetter(helpchar)||Character.isWhitespace(helpchar)){
                        sb.append(helpchar);
                    }
                }
                sb.append("\n");
            }

            FileWriter fw = new FileWriter("C:\\Users\\Loic PC\\Documents\\IntelliJ_java_Course\\1DV507\\src\\lg222sv_assign3\\count_words\\words.txt");
            fw.write(sb.toString());
            fw.close();
        }catch(IOException ioe){System.out.println("Please provide a correct path to the file");}




    }
}
