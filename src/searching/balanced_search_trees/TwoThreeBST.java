package searching.balanced_search_trees;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class TwoThreeBST<K extends Comparable<K>, V> {
    private Node root;

    public static void main(String[] args) {
        TwoThreeBST<Integer, Character> bst = new TwoThreeBST<>();
        bst.insert(0, 'S');
        bst.insert(1, 'E');
        bst.insert(2, 'A');
        bst.insert(3, 'R');
        bst.insert(4, 'C');
        bst.insert(5, 'H');
        bst.insert(6, 'X');
        bst.insert(7, 'M');
        bst.insert(8, 'P');
        bst.insert(9, 'L');
        bst.print();
        bst.search(5).ifPresent(StdOut::println); // H
        bst.search(4).ifPresent(StdOut::println); // C
    }

    public Optional<V> search(K key) {
        return Optional.ofNullable(root)
                .flatMap(n -> n.get(key));
    }

    public void insert(K key, V val) {
        if (root != null) {
            root.merge(root.insert(key, val));
        } else {
            root = new Node(key, val);
        }
    }

    public void print() {
        if (root != null) {
            root.print();
            StdOut.println();
        }
    }

    private class Node {
        private static final int LEFT = 0, MIDDLE = 1, RIGHT = 2, TEMP = 3;
        private List<KeyValue> kvs;
        private List<Node> links;

        private Node(KeyValue kv) {
            kvs = new ArrayList<>();
            kvs.add(kv);
            links = new ArrayList<>();
        }

        private Node(K k, V v) {
            this(new KeyValue(k, v));
        }

        private Node(KeyValue kv, Node link) {
            this(kv);
            if (link != null)
                links.add(link);
        }

        private Node(KeyValue kv, Node left, Node right) {
            this(kv, left);
            if (right != null)
                links.add(right);
        }

        private void print() {
            StdOut.print("{");
            kvs.forEach(StdOut::print);
            links.forEach(Node::print);
            StdOut.print("}");
        }

        private Optional<V> get(K key) {
            KeyValue kv1 = kvs.get(0);
            int cmp1 = key.compareTo(kv1.key);
            if (cmp1 < 0) {
                return getValue(LEFT, key);
            } else if (cmp1 > 0) {
                if (kvs.size() == 2) {
                    KeyValue kv2 = kvs.get(1);
                    int cmp2 = key.compareTo(kv2.key);
                    if (cmp2 < 0)
                        return getValue(MIDDLE, key);
                    else if (cmp2 > 0)
                        return getValue(RIGHT, key);
                    else
                        return Optional.of(kv2.value);
                } else {
                    return getValue(RIGHT, key);
                }
            } else {
                return Optional.of(kv1.value);
            }
        }

        private Optional<V> getValue(int pos, K key) {
            Node n = getLink(pos);
            if (n != null)
                return n.get(key);
            else
                return Optional.empty();
        }

        private Node insert(K key, V val) {
            int cmp1 = key.compareTo(kvs.get(0).key);
            if (cmp1 < 0) {
                insertInto(LEFT, key, val);
            } else if (cmp1 > 0) {
                if (kvs.size() == 2) {
                    int cmp2 = key.compareTo(kvs.get(1).key);
                    if (cmp2 < 0)
                        insertInto(MIDDLE, key, val);
                    else if (cmp2 > 0)
                        insertInto(RIGHT, key, val);
                    else
                        kvs.get(1).value = val;
                } else {
                    addKeyValue(new KeyValue(key, val));
                }
            } else {
                kvs.get(0).value = val;
            }
            if (kvs.size() > 2) {
                Node left = new Node(kvs.get(0), getLink(LEFT), getLink(MIDDLE));
                Node right = new Node(kvs.get(2), getLink(RIGHT), getLink(TEMP));
                return new Node(kvs.get(1), left, right);
            }
            return null;
        }

        private void insertInto(int pos, K key, V val) {
            Node n = getLink(pos);
            if (n != null)
                n.merge(n.insert(key, val));
            else
                addKeyValue(new KeyValue(key, val));
        }

        private void merge(Node node) {
            if (node != null) {
                kvs = node.kvs;
                links = node.links;
                sortKvs();
                sortNodes();
            }
        }

        private void addKeyValue(KeyValue kv) {
            kvs.add(kv);
            sortKvs();
        }

        private Node getLink(int pos) {
            if (pos == LEFT && !links.isEmpty())
                return links.get(LEFT);
            else if (pos == MIDDLE && links.size() == 3)
                return links.get(MIDDLE);
            else if (pos == RIGHT && links.size() == 2)
                return links.get(1);
            else if (pos == RIGHT && links.size() == 3)
                return links.get(RIGHT);
            else if (pos == TEMP && links.size() == 4)
                return links.get(TEMP);
            else
                return null;
        }

        private void sortNodes() {
            if (links.size() > 1) {
                links.sort((n1, n2) -> {
                    K min1 = n1.kvs.get(0).key;
                    K max1 = n1.kvs.get(n1.kvs.size() - 1).key;
                    K min2 = n2.kvs.get(0).key;
                    K max2 = n2.kvs.get(n2.kvs.size() - 1).key;
                    int minCmp = min1.compareTo(min2);
                    int maxCmp = max1.compareTo(max2);
                    if (minCmp < 0 && maxCmp < 0)
                        return -1;
                    else if (minCmp > 0 && maxCmp > 0)
                        return 1;
                    else
                        return Math.abs(maxCmp) - Math.abs(minCmp);
                });
            }
        }

        private void sortKvs() {
            if (kvs.size() > 1)
                kvs.sort(Comparator.comparing(o -> o.key));
        }
    }

    private class KeyValue {
        private K key;
        private V value;

        private KeyValue(K k, V v) {
            key = k;
            value = v;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }
}