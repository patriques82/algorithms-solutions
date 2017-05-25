package searching.balanced_search_trees;

import edu.princeton.cs.algs4.StdOut;

public class RedBlackBST<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    public static void main(String[] args) {
        RedBlackBST<Character, Integer> bst = new RedBlackBST<>();
        bst.put('A', 0);
        bst.put('C', 1);
        bst.put('E', 2);
        bst.put('H', 3);
        bst.put('L', 4);
        bst.put('M', 5);
        bst.put('P', 6);
        bst.put('R', 7);
        bst.put('S', 8);
        bst.put('X', 9);
        bst.print();
    }

    public void put(K key, V val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, K key, V val) {
        if (h == null)
            return new Node(key, val, RED);

        int cmp = key.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left, key, val);
        else if (cmp > 0)
            h.right = put(h.right, key, val);
        else
            h.val = val;

        if (isRed(h.left) && isRed(h.right))
            flipColors(h);
        else if (isRed(h.right) && !isRed(h.left)) // we want left learning red-black trees
            h = rotateLeft(h);
        else if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        return h;
    }

    public void print() {
        if (root != null) {
            root.print();
            StdOut.println();
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private class Node {
        K key;
        V val;
        Node left, right;
        boolean color;
        Node(K key, V val, boolean col) {
            this.key = key;
            this.val = val;
            this.color = col;
        }

        public void print() {
            StdOut.print("{");
            StdOut.print(key);
            if (left != null) left.print();
            if (right != null) right.print();
            StdOut.print("}");
        }
    }
}
