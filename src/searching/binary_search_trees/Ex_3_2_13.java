package searching.binary_search_trees;

import edu.princeton.cs.algs4.StdOut;

/**
 * Give nonrecursive implementations of get() and put() for BST.
 */
public class Ex_3_2_13 {

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
            Node curr = root;
            while (curr != null) {
                int cmp = key.compareTo(curr.key);
                if (cmp < 0) curr = curr.left;
                else if (cmp > 0) curr = curr.right;
                else return curr.val;
            }
            return null;
        }

        public void put(K key, V val) {
            Node newNode = new Node(key, val);
            if (root == null)
                root = newNode;
            else {
                Node curr = root;
                while (true) {
                    int cmp = key.compareTo(curr.key);
                    if (cmp < 0) {
                        if (curr.left == null) {
                            curr.left = newNode;
                            break;
                        }
                        curr = curr.left;
                    } else if (cmp > 0) {
                        if (curr.right == null) {
                            curr.right = newNode;
                            break;
                        }
                        curr = curr.right;
                    } else {
                        curr.val = val;
                        break;
                    }
                }
            }
        }

    }
}
