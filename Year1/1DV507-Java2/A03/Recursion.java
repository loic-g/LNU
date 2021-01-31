package lg222sv_assign3;

public class Recursion {

    public static void main(String[] args){
        int[] a = {1,2,3,4,5,6,7,8,9,10};
        printBackwards(a,1);

    }



    public static void printBackwards(int[] arr,int n){
        if(n<0 || n>arr.length){
            throw new IndexOutOfBoundsException("NOOOOOO");
        }else if(n==arr.length-1){
            System.out.print(arr[arr.length-1-n]+" ");
        }else{
            System.out.print(arr[arr.length-1-n]+ " ");
            printBackwards(arr,n+1);
            String test = "WDEFEFEFEF";
            String t = test.substring(1,test.length()-1);
            StringBuilder sb = new StringBuilder();
        }
    }
}
