package _3_searching._4_hash_tables;

import edu.princeton.cs.algs4.StdOut;

/**
 Develop an alternate implementation of SeparateChainingHashST that directly
 uses the linked-list code from SequentialSearchST
 */
public class Ex_3_4_2 {

    public static void main(String[] args) {
        SeparateChainingHashST<Character, Integer> st = new SeparateChainingHashST<>(4);
        st.put('E', 0);
        st.put('A', 1);
        st.put('S', 2);
        st.put('Y', 3);
        st.put('Q', 4);
        st.put('U', 5);
        st.put('T', 6);
        st.put('I', 7);
        st.put('O', 8);
        st.put('N', 9);
        StdOut.println(st.get('Q')); // 4
    }

    public static class SeparateChainingHashST<K, V> {
        private Node<K, V>[] arr;
        private int M;

        public SeparateChainingHashST(int M) {
            this.M = M;
            this.arr = (Node<K, V>[]) new Node[M];
        }

        public void put(K key, V val) {
            int h = hash(key);
            if (arr[h] == null) {
                arr[h] = new Node<>(key, val);
            } else {
                Node<K, V> n = arr[h];
                while (n.next != null) {
                    n = n.next;
                }
                n.next = new Node<>(key, val);
            }
        }

        public V get(K key) {
            Node<K, V> n = arr[hash(key)];
            while (n != null) {
                if (n.key.equals(key)) {
                    return n.val;
                }
                n = n.next;
            }
            return null;
        }

        private int hash(K key) {
            return hash(key, 11);
        }

        private int hash(K key, int a) {
            return a * (key.hashCode() & 0x7fffffff) % M;
        }

        private class Node<K, V> {
            private Node<K, V> next;
            private K key;
            private V val;

            private Node(K key, V val) {
                this.key = key;
                this.val = val;
            }
        }
    }

}