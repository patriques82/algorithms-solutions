package _3_searching._2_binary_search_trees;

import edu.princeton.cs.algs4.StdOut;

import java.util.PriorityQueue;

/**
 * Give nonrecursive implementations of min(), max(), floor(), ceiling(),
 * rank(), and select().
 */
public class Ex_3_2_14 {

    public static void main(String[] args) {
        BST<Character, Integer> bst = new BST<>();
        bst.put('S', 1);
        bst.put('E', 2);
        bst.put('X', 3);
        bst.put('A', 4);
        bst.put('R', 5);
        bst.put('C', 6);
        bst.put('H', 7);
        bst.put('M', 8);

        StdOut.println("min: " + bst.min()); // A
        StdOut.println("max: " + bst.max()); // X
        StdOut.println("floor G: " + bst.floor('G')); // E
        StdOut.println("ceiling G: " + bst.ceiling('G')); // H
        StdOut.println("size: " + bst.size()); // 8

        StdOut.println("rank S: " + bst.rank('S')); // 6
        StdOut.println("rank A: " + bst.rank('A')); // 0
        StdOut.println("rank E: " + bst.rank('E')); // 2
        StdOut.println("rank R: " + bst.rank('R')); // 5
        StdOut.println("rank X: " + bst.rank('X')); // 7

        StdOut.println("select 6: " + bst.select(6)); // S
        StdOut.println("select 0: " + bst.select(0)); // A
        StdOut.println("select 2: " + bst.select(2)); // E
        StdOut.println("select 5: " + bst.select(5)); // R
        StdOut.println("select 7: " + bst.select(7)); // X
    }

    public static class BST<K extends Comparable<K>, V> {

        private Node root;

        private class Node implements Comparable<Node> {
            private K key;
            private V val;
            private Node left;
            private Node right;

            private Node(K k, V v) {
                key = k;
                val = v;
            }

            @Override
            public int compareTo(Node other) {
                return other.key.compareTo(key); // reverse compare for PQ
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

        /**
         * Recursive def:
         *
         * Minimum and maximum. If the left link of the root is null, the smallest key in a
         * BST is the key at the root; if the left link is not null, the smallest key in the
         * BST is the smallest key in the subtree rooted at the node referenced by the left
         * link. Finding the maximum key is similar, moving to the right instead of to the left.
         */
        public K min() {
            if (root == null)
                return null;
            else {
                Node curr = root;
                while (true) {
                    if (curr.left != null)
                        curr = curr.left;
                    else
                        return curr.key;
                }
            }
        }

        public K max() {
            if (root == null)
                return null;
            else {
                Node curr = root;
                while (true) {
                    if (curr.right != null)
                        curr = curr.right;
                    else
                        return curr.key;
                }
            }
        }

        /**
         * Recursive def:
         *
         * Floor and ceiling. If a given key is less than the key at the root of a BST,
         * then the floor of key (the largest key in the BST less than or equal to key) must
         * be in the left subtree. If key is greater than the key at the root, then the floor
         * of key could be in the right subtree, but only if there is a key smaller than or
         * equal to key in the right subtree; if not (or if key is equal to the key at the
         * root) then the key at the root is the floor of key. Finding the ceiling is similar,
         * interchanging right and left.
         */
        public K floor(K key) {
            if (root == null)
                return null;
            else {
                Node tmp = null;
                Node curr = root;
                while (true) {
                    if (curr == null) {
                        if (tmp != null)
                            return tmp.key;
                        return null;
                    }
                    int cmp = key.compareTo(curr.key);
                    if (cmp == 0)
                        return curr.key;
                    else if (cmp < 0)
                        curr = curr.left;
                    else {
                        tmp = curr;
                        curr = curr.right;
                    }
                }
            }
        }

        public K ceiling(K key) {
            if (root == null)
                return null;
            else {
                Node tmp = null;
                Node curr = root;
                while (true) {
                    if (curr == null) {
                        if (tmp != null)
                            return tmp.key;
                        return null;
                    }
                    int cmp = key.compareTo(curr.key);
                    if (cmp == 0)
                        return curr.key;
                    else if (cmp > 0)
                        curr = curr.right;
                    else {
                        tmp = curr;
                        curr = curr.left;
                    }
                }
            }
        }

        /**
         * Recursive def:
         *
         * Selection. Suppose that we seek the key of rank k (the key such that precisely k other
         * keys in the BST are smaller). If the number of keys t in the left subtree is larger
         * than k, we look (recursively) for the key of rank k in the left subtree; if t is equal
         * to k, we return the key at the root; and if t is smaller than k, we look (recursively)
         * for the key of rank k - t - 1 in the right subtree.
         */
        public K select(int k) {
            if (root == null) {
                return null;
            } else {
                int size = 0;
                Node curr = root;
                while (curr != null) {
                    int leftSize = size(curr.left);
                    if (leftSize + size > k) {
                        curr = curr.left;
                    } else if (leftSize + size < k) {
                        size += leftSize + 1;
                        curr = curr.right;
                    } else {
                        break;
                    }
                }
                if (curr == null)
                    return null;
                return curr.key;
            }
        }

        /**
         * Recursive def:
         *
         * Rank. If the given key is equal to the key at the root, we return the number of keys t
         * in the left subtree; if the given key is less than the key at the root, we return the
         * rank of the key in the left subtree; and if the given key is larger than the key at the
         * root, we return t plus one (to count the key at the root) plus the rank of the key in
         * the right subtree.
         */
        public int rank(K key) {
            int r = 0;
            if (root != null) {
                Node curr = root;
                while (curr != null) {
                    int cmp = key.compareTo(curr.key);
                    if (cmp > 0) {
                        r += size(curr.left) + 1;
                        curr = curr.right;
                    } else if (cmp < 0) {
                        curr = curr.left;
                    } else {
                        r += size(curr.left);
                        break;
                    }
                }
            }
            return r;
        }

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            PriorityQueue<Node> queue = new PriorityQueue<>();
            int N = 0;
            if (node != null) {
                queue.add(node);
                while (!queue.isEmpty()) {
                    Node n = queue.remove();
                    N++;
                    if (n.left != null)
                        queue.add(n.left);
                    if (n.right != null)
                        queue.add(n.right);
                }
            }
            return N;
        }

    }
}