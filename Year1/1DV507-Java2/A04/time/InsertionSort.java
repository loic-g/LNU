package lg222sv_assign4.time;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class InsertionSort {
    static int intRange = 9000; // RANGE OF THE NUMBERS IN THE INT ARRAY
    static int stringRange = 10; // SIZE OF THE STRINGS TO SORT IN THE ARRAY
    static int timeMeasurement = 1; // TIME TO MEASURE, IN SECONDS

    private static int[] insertionSort(int[] in) {


        if (in.length < 2) {
            return in;
        }


        int[] sortedInt = Arrays.copyOf(in, in.length);


        for (int i = 1; i < in.length; i++) {

            int pos = i;


            while (pos > 0 && sortedInt[pos] < sortedInt[pos - 1]) {

                int temp = sortedInt[pos];
                sortedInt[pos] = sortedInt[pos - 1];
                sortedInt[pos - 1] = temp;

                pos--;
            }
        }

        return sortedInt;
    }

    private static String[] insertionSort(String[] in, Comparator<String> c) {


        if (in.length < 2) {
            return in;
        }


        String[] sortedStrings = Arrays.copyOf(in, in.length);


        for (int i = 1; i < in.length; i++) {

            int pos = i;

            // COMPARES AND SWAPS POS AND POS-1
            while (pos > 0 && c.compare(sortedStrings[pos], sortedStrings[pos - 1]) < 0) {
                String temp = sortedStrings[pos];
                sortedStrings[pos] = sortedStrings[pos - 1];
                sortedStrings[pos - 1] = temp;

                pos--;
            }
        }

        return sortedStrings;
    }

    private static String randomStringGenerator(int size) throws IllegalArgumentException {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        Random randToFill = new Random();
        String randStr = "";
        for (int i = 0; i < size; i++) {
            randStr = randStr + "" + ((char) (randToFill.nextInt(26) + 'a'));
        }
        return randStr;
    }

    private static int[] randomIntArr(int size, int range) throws IllegalArgumentException {
        if (size < 0 || range <= 0) {
            throw new IllegalArgumentException();
        }

        int[] array = new int[size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(range);
        }

        return array;
    }

    private static String[] randomStringArr(int size, int stringSize) throws IllegalArgumentException {
        if (size < 0 || stringSize <= 0) {
            throw new IllegalArgumentException();
        }

        String[] array = new String[size];

        for (int i = 0; i < size; i++) {
            array[i] = randomStringGenerator(stringSize);
        }

        return array;
    }

    private static long insertionSortMeasureTime(int[] arr) {
        long before;
        long after;
        before = System.currentTimeMillis();
        arr = insertionSort(arr); // BAD PRACTICE, ETC
        after = System.currentTimeMillis();

        return after - before;
    }

    private static long insertionSortMeasureTime(String[] arr) {
        long before;
        long after;
        before = System.currentTimeMillis();
        arr = insertionSort(arr, (String s1, String s2) -> s1.compareTo(s2));
        after = System.currentTimeMillis();

        return after - before;
    }

    public static void main(String[] args) {
        double[] results = new double[4];
        int[] intArr;
        String[] strArr;


            long time = 0;
            int count = 100;

            // INT
            do {
                intArr = randomIntArr(count, intRange);
                time = insertionSortMeasureTime(intArr);

                if (time > timeMeasurement * 1000) {
                    count = count / 2;
                }

                else {
                    count = count + count / 2;
                }

            } while (time < timeMeasurement * 975 || time > timeMeasurement * 1025);

            System.out.println("int array, size: " + intArr.length + " time to sort: " + time / 1000.0);

            results[0] = results[0] + intArr.length;
            results[1] = results[1] + time / 1000.0;

            time = 0;
            count = 100;

            // STRING
            do {
                strArr = randomStringArr(count, stringRange);
                time = insertionSortMeasureTime(strArr);

                if (time > timeMeasurement * 1000) {
                    count = count / 2;
                }

                else {
                    count = count + count / 2;
                }

            } while (time < timeMeasurement * 975 || time > timeMeasurement * 1025);

            System.out.println("str array, size: " + strArr.length + " time to sort: " + time / 1000.0);

            results[2] = results[2] + strArr.length;
            results[3] = results[3] + time / 1000.0;


        for (int i = 0; i < results.length; i++) {
            results[i] = results[i];
        }

    }
}
