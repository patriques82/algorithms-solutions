package _3_searching._4_hash_tables;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;
import java.util.Set;

/**
 * Write a program to find values of a and M, with M as small as possible, such that
 * the hash function (a * k) % M for transforming the kth letter of the alphabet into a
 * table index produces distinct values (no collisions) for the keys S E A R C H X M P L.
 * The result is known as a perfect hash function.
 */
public class Ex_3_4_4 {
    private static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};
    private static final String EX = "SEARCHXMPL";

    public static void main(String[] args) {
        int M = EX.length();
        int a = 0;
        boolean found = false;
        while (!found) {
            a = 0;
            while (a < PRIMES.length) {
                if (isPerfectHash(PRIMES[a], M)) {
                    found = true;
                    break;
                }
                a++;
            }
            M++;
        }
        StdOut.println("Perfect hash: " + PRIMES[a] + ", " + M); // Perfect hash: 3, 21
    }

    private static boolean isPerfectHash(int a, int M) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < EX.length(); i++) {
            int h = (a * EX.charAt(i)) % M; // hash
            if (set.contains(h))
                return false;
            set.add(h);
        }
        return true;
    }

}
