package _2_sorting._3_quicksort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Quick {

    private static Comparable[] aux;

    public static void main(String[] args) {
        Integer[] array = {0, 9, 2, 7, 6, 4, 3, 8, 1, 5, 11, 12, 14, 18, 16, 17, 19, 10, 13, 15};
        sort(array);
        Arrays.stream(array).forEach(x -> StdOut.print(x + " "));
    }

    public static void sort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        System.arraycopy(arr, 0, aux, 0, aux.length);
        sort(arr, 0, arr.length-1);
    }

    private static void sort(Comparable[] arr, int start, int end) {
        if(end - start > 0) {
            Comparable x = arr[start];
            int i = start;
            int j = end;
            for (int k = start+1; k <= end; k++) {
                if (less(aux[k], x)) {
                    arr[i++] = aux[k];
                } else {
                    arr[j--] = aux[k];
                }
            }
            arr[i] = x;
            System.arraycopy(arr, start, aux, start, end-start+1);
            sort(arr, start, i-1);
            sort(arr, i+1, end);
        }
    }

    private static boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

}

