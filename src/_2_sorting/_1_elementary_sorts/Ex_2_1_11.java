package _2_sorting._1_elementary_sorts;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Implement a version of shellsort that keeps the increment sequence in an array,
 * rather than computing it.
 */
public class Ex_2_1_11 {

    public static void main(String[] args) {
        Integer[] array = {0, 9, 2, 7, 6, 4, 3, 8, 1, 5, 11, 12, 14, 18, 16, 17, 19, 10, 13, 15};
        Shell.sort(array, Shell.incrementSeq(array.length));
        Arrays.stream(array).forEach(x -> StdOut.print(x + " "));
    }

    public static class Shell {

        static int[] incrementSeq(int size) {
            int[] seq = new int[(int) (Math.log(size)/Math.log(3))];
            seq[seq.length-1] = 1;
            for(int i = seq.length-2; i >= 0; i--) {
                seq[i] = seq[i+1] * 3 + 1;
            }
            return seq;
        }

        static void sort(Comparable[] a, int[] incrementSeq) {
            int N = a.length;
            // int h = 1;
            // while(h < N/3) h = 3 * h + 1;
            // while(h >= 1) {
            for (int h : incrementSeq) {
                for (int i = 0; i < N; i++) {
                    for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                        swap(a, j - h, j);
                    }
                }
                // h /= 3;
            }
        }

        private static void swap(Comparable[] a, int x, int y) {
            Comparable temp = a[x];
            a[x] = a[y];
            a[y] = temp;
        }

        private static boolean less(Comparable x, Comparable y) {
            return x.compareTo(y) < 0;
        }
    }
}
