package _2_sorting._2_mergesort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Merge {

    private static Comparable[] aux;

    public static void main(String[] args) {
        Integer[] array = {0, 9, 2, 7, 6, 4, 3, 8, 1, 5, 11, 12, 14, 18, 16, 17, 19, 10, 13, 15};
        sort(array);
        Arrays.stream(array).forEach(x -> StdOut.print(x + " "));
    }

    static void sort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        System.arraycopy(arr, 0, aux, 0, aux.length);
        sort(arr, 0, arr.length-1);
    }

    private static void sort(Comparable[] arr, int start, int end) {
        if (end - start > 0) {
            int mid = start + (end - start)/2;
            sort(arr, start, mid);
            sort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    private static void merge(Comparable[] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        System.arraycopy(arr, start, aux, start, end - start + 1);
        for (int k = start; k <= end; k++) {
            if      (i > mid)               arr[k] = aux[j++];
            else if (j > end)               arr[k] = aux[i++];
            else if (less(aux[i], aux[j]))  arr[k] = aux[i++];
            else                            arr[k] = aux[j++];
        }
    }

    private static boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }
}
