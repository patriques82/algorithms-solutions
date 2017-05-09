package sorting.mergesort;

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

        public static void sort(Comparable[] a) {
            Comparable[] aux = new Comparable[a.length];
            sort(a, 0, a.length - 1, aux);
        }

        private static void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
            if (hi <= lo) return;
            int mid = lo + (hi - lo)/2;
            sort(a, lo, mid, aux);
            sort(a, mid+1, hi, aux);
            merge(a, lo, mid, hi, aux);
        }

        private static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
            for (int k = lo; k <= hi; k++)
                aux[k] = a[k];
            int i = lo;
            int j = mid+1;
            for (int k = lo; k <= hi; k++) {
                if (i > mid)                    a[k] = aux[j++];
                else if (j > hi)                a[k] = aux[i++];
                else if (less(aux[i], aux[j]))  a[k] = aux[i++];
                else                            a[k] = aux[j++];
            }
        }

        private static boolean less(Comparable x, Comparable y) {
            return x.compareTo(y) < 0;
        }
    }
}
