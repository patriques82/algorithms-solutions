package _3_searching._2_binary_search_trees;

import edu.princeton.cs.algs4.StdOut;

/**
 Develop a BST implementation that omits rank() and select() and does not
 use a count field in Node.
 */
public class Ex_3_2_12 {

    public static void main(String[] args) {
        BST<Integer, Character> bst = new BST<>();
        bst.put(4, 'A');
        bst.put(2, 'B');
        bst.put(6, 'C');
        bst.put(1, 'D');
        bst.put(3, 'E');
        bst.put(5, 'F');
        bst.put(7, 'G');
        StdOut.println(bst.get(4) + "-" + bst.get(7)); // A-G
    }

    public static class BST<K extends Comparable<K>, V> {

        private Node root;

        private class Node {
            private K key;
            private V val;
            private Node left;
            private Node right;

            private Node(K k, V v) {
                key = k;
                val = v;
            }
        }

        public V get(K key) {
            return get(root, key);
        }

        private V get(Node n, K key) {
            if (n == null)
                return null;
            int cmp = key.compareTo(n.key);
            if (cmp < 0)
                return get(n.left, key);
            else if (cmp > 0)
                return get(n.right, key);
            else
                return n.val;
        }

        public void put(K key, V val) {
            root = put(root, key, val);
        }

        private Node put(Node n, K key, V val) {
            if (n == null)
                return new Node(key, val);
            int cmp = key.compareTo(n.key);
            if (cmp < 0)
                n.left = put(n.left, key, val);
            else if (cmp > 0)
                n.right = put(n.right, key, val);
            else
                n.val = val;
            return n;
        }

    }

}
