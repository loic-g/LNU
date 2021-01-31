package lg222sv_assign4.time;
public class StringConcatenation {
    public static void main(String[] args){

        StringConcatenationSS();
        StringConcatenationLS();
        StringConcatenationSBS();
        StringConcatenationSBL();

    }

    public static void StringConcatenationSS(){
        String ShortString ="";
        long before = System.currentTimeMillis();
        while (System.currentTimeMillis()-before<1000){
            ShortString = ShortString + "*";
        }
        long after = System.currentTimeMillis();
        int time = (int)(after - before);

        System.out.println("String Concatenation for Short String");
        System.out.println("Time taken: "+time
                          +"\n# of Concatenation in 1 sec: "+ShortString.length()
                          +"\nString length: "+ShortString.length());
        System.out.println();
    }
    public static void StringConcatenationLS(){
        String LongString ="";
        long before = System.currentTimeMillis();
        while (System.currentTimeMillis()-before<1000){
            LongString= LongString+"********************************************************************************";
        }
        long after = System.currentTimeMillis();

        int timetaken = (int)(after-before);

        System.out.println("String Concatenation for Long String");
        System.out.println("Time taken: "+timetaken
                          +"\n# of Concatenation in 1 sec: "+LongString.length()/80
                          +"\nString length: "+LongString.length());
        System.out.println();

    }
    public static void StringConcatenationSBS(){
        /* Testing to be able to get the time taken for the the toString method*/
        StringBuilder sb = new StringBuilder();
        long beforetest = System.currentTimeMillis();
        while(System.currentTimeMillis()-beforetest<1000){
            sb.append("*");
        }
        long aftertest = System.currentTimeMillis();
        long beforetoString = System.currentTimeMillis();
        String help = sb.toString();
        long aftertoString = System.currentTimeMillis();
        int time = (int) (aftertoString-beforetoString);
        /* Actually checking how many concatenation per seconds */
        StringBuilder SBS = new StringBuilder();

        long before = System.currentTimeMillis();
        while(System.currentTimeMillis()-before<1000-time){
            SBS.append("*");
        }
        String LongString = SBS.toString();
        long after = System.currentTimeMillis();
        System.out.println("String Concatenation with StringBuilder for Short String");
        System.out.println("Time taken: "+(after-before)
                          +"\n#of Concatenation in 1 sec: "+LongString.length()
                          +"\nString length: "+LongString.length());
        System.out.println();
    }
    public static void StringConcatenationSBL(){
        StringBuilder sb = new StringBuilder();

        long beforetest = System.currentTimeMillis();
        while (System.currentTimeMillis()-beforetest<1000){
            sb.append("********************************************************************************");
        }
        long beforetoString = System.currentTimeMillis();
        String help = sb.toString();
        long aftertoString = System.currentTimeMillis();
        int timetoString = (int)(aftertoString-beforetoString);

        StringBuilder SBL = new StringBuilder();
        long before = System.currentTimeMillis();
        while (System.currentTimeMillis()-before<(1000-timetoString)){
            SBL.append("********************************************************************************");
        }
        String StringSBL = SBL.toString();
        long after = System.currentTimeMillis();
        System.out.println("String Concatenation with StringBuilder for Long String");
        System.out.println("Time taken(ms): " + (after-before));
        System.out.println("# of concatenation of 1 sec with a StringBuilder using Long String: "+StringSBL.length()/80);
        System.out.println("String length for StringBuilder using Long String: "+StringSBL.length());
        System.out.println();

    }

}
