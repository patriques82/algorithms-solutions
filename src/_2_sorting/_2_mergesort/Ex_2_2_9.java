package _2_sorting._2_mergesort;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Use of a static array like aux[] is inadvisable in library software because multiple
 * clients might use the class concurrently. Give an implementation of Merge that does not
 * use a static array. Do not make aux[] local to merge() (see the Q&A for this section).
 * Hint : Pass the auxiliary array as an argument to the recursive sort().
 */
public class Ex_2_2_9 {

    public static void main(String[] args) {
        Integer[] array = {0, 9, 2, 7, 6, 4, 3, 8, 1, 5, 11, 12, 14, 18, 16, 17, 19, 10, 13, 15};
        Merge.sort(array);
        Arrays.stream(array).forEach(x -> StdOut.print(x + " "));
    }

    public static class Merge {
        // private static Comparable[] aux;

        public static void sort(Comparable[] arr) {
            Comparable[] aux = new Comparable[arr.length];
            sort(arr, 0, arr.length - 1, aux);
        }

        private static void sort(Comparable[] arr, int lo, int hi, Comparable[] aux) {
            if (hi <= lo) return;
            int mid = lo + (hi - lo)/2;
            sort(arr, lo, mid, aux);
            sort(arr, mid+1, hi, aux);
            merge(arr, lo, mid, hi, aux);
        }

        private static void merge(Comparable[] arr, int start, int mid, int end, Comparable[] aux) {
            System.arraycopy(arr, start, aux, start, end - start + 1);
            int i = start;
            int j = mid+1;
            for (int k = start; k <= end; k++) {
                if (i > mid)                    arr[k] = aux[j++];
                else if (j > end)                arr[k] = aux[i++];
                else if (less(aux[i], aux[j]))  arr[k] = aux[i++];
                else                            arr[k] = aux[j++];
            }
        }

        private static boolean less(Comparable x, Comparable y) {
            return x.compareTo(y) < 0;
        }
    }
}
