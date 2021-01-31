package lg222sv_assign3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SortingAlgorithmsTest {

    SortingAlgorithms SA = new SortingAlgorithms();

    int[] singleArrayInt = {9};
    int[] fewIntArray = {8,85,74,-8,215,1,615,5,55};
    int[] fewIntArraySorted ={-8,1,5,8,55,74,85,215,615};

    int[] hugeIntArray=createBigArray();
    int[] hugeIntArraySorted= new int[hugeIntArray.length];



    String[] singleArrayString = {"Hello"};
    String[] fewStringArray = {"Mango","Apple","Peach","Raspberries","Orange"};
    String[] FewStringArraySorted ={"Apple","Mango","Orange","Peach","Raspberries"};

    @Test
    public void insertionSortTest(){

        System.arraycopy(hugeIntArray,0,hugeIntArraySorted,0,hugeIntArray.length);
        Arrays.sort(hugeIntArraySorted);


        /*      Single Element Array Testing      */
        assertArrayEquals(singleArrayInt,SA.insertionSort(singleArrayInt));
        /*      Few Elements Array Testing      */
        assertArrayEquals(fewIntArraySorted,SA.insertionSort(fewIntArray));
        /*      A lot of Elements Array Testing      */
        assertArrayEquals(hugeIntArraySorted,SA.insertionSort(hugeIntArray));

    }

    @Test
    public void insertionSortStringTest(){
        Comparator<String> myComp = (s1,s2)-> s1.compareTo(s2);

        assertArrayEquals(singleArrayString,SA.insertionSort(singleArrayString,myComp));

        assertArrayEquals(FewStringArraySorted,SA.insertionSort(fewStringArray,myComp));

    }

    private int[] createBigArray(){
        Random rd = new Random();
        int[] list = new int[5000];


        for(int i=0;i<5000;i++){
            int rand = rd.nextInt();
            list[i]=rand;
        }

        return list;
    }
}