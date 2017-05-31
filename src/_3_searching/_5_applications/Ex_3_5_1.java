package _3_searching._5_applications;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

/**
 Implement SET and HashSET as “wrapper class” clients of ST and HashST, respectively
 (provide dummy values and ignore them).
 */
public class Ex_3_5_1 {

    public static void main(String[] args) {
        Set<Integer> set = new Set<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.delete(3);
        StdOut.println(set.contains(2)); // true
        StdOut.println(set.isEmpty()); // false
        StdOut.println(set.size()); // 2
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.delete(3);
        StdOut.println(hashSet.contains(2)); // true
        StdOut.println(hashSet.isEmpty()); // false
        StdOut.println(hashSet.size()); // 2
    }

    private static class Set<Key extends Comparable<Key>> {
        private ST<Key, Object> delegate = new ST<>();

        private void add(Key key) {
            delegate.put(key, new Object());
        }

        private void delete(Key key) {
            delegate.delete(key);
        }

        private boolean contains(Key key) {
            return delegate.contains(key);
        }

        private boolean isEmpty() {
            return delegate.isEmpty();
        }

        private int size() {
            return delegate.size();
        }

        @Override
        public String toString() {
            return delegate.toString();
        }
    }

    private static class HashSet<Key extends Comparable<Key>> {
        private LinearProbingHashST<Key, Object> delegate = new LinearProbingHashST<>(20);

        private void add(Key key) {
            delegate.put(key, new Object());
        }

        private void delete(Key key) {
            delegate.delete(key);
        }

        private boolean contains(Key key) {
            return delegate.contains(key);
        }

        private boolean isEmpty() {
            return delegate.isEmpty();
        }

        private int size() {
            return delegate.size();
        }

        @Override
        public String toString() {
            return delegate.toString();
        }

    }

}
