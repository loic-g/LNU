package lg222sv_assign3.count_words;

public class Word implements Comparable<Word>{
    private String word;

    public Word(String str) {
        word = str.toLowerCase();
    }
    public String getWord(){return word;}
    public void setWord(String str){
        word = str;
    }
    public String toString() {return word;}
    // compute a hash value for word /* Override Object methods */
    @Override
    public int hashCode() {
        setWord(getWord().toLowerCase());
        int hashcodeValue=0;
        for (int i=0;i<word.length();i++){
            char help = getWord().charAt(i);
            int hashcodetry = Character.valueOf(help);
            hashcodeValue+=hashcodetry;
        }
        return hashcodeValue;
    }
    //true if two words are equal
    @Override
    public boolean equals(Object other) {
        if(other instanceof Word){
            Word wordother = (Word)other;
            return (this.getWord().hashCode())==(wordother.getWord().hashCode());
        }
        else
            return false;
    }
    // compares two words lexicographically/* Implement Comparable */
    @Override
    public int compareTo(Word w) {
        return this.getWord().compareTo(w.getWord());
    }
}
