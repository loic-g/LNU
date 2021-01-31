package lg222sv_assign3.count_words;

public class WordMain {
    public static void main(String[] args){
        Word w = new Word("Hello");
        Word wd = new Word("hELlo");
        System.out.println(w.hashCode());
        System.out.println(wd.hashCode());
        System.out.println(w.equals(wd));

    }
}
