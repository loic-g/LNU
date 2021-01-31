package lg222sv_assign3.NorseGoods;

import lg222sv_assign3.SortingAlgorithms;

import java.util.Arrays;
import java.util.Comparator;

public class SortingAlgorithmsMain {
    public static void main(String[] args){
        SortingAlgorithms SA = new SortingAlgorithms();
        int[] test = {1,7,2,9,5,3,4};
        int[] test2 = SA.insertionSort(test);
        System.out.println("Original int Array: "+ Arrays.toString(test));
        System.out.println(Arrays.toString(test2));

        String[] stringtest = {"xylophone","allo","hej","class","elephant","hello"};
        Comparator<String> myComparator = (s1, s2)-> s1.compareTo(s2);
        String[] insertSortString = SA.insertionSort(stringtest,myComparator);
        System.out.println("Original String Array: "+Arrays.toString(stringtest));
        System.out.println(Arrays.toString(insertSortString));
    }
}
