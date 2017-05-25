package searching.balanced_search_trees;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class TwoThreeBST<K extends Comparable<K>, V> {
    private Node root;

    public static void main(String[] args) {
        TwoThreeBST<Character, Integer> bst = new TwoThreeBST<>();
        bst.insert('S', 0);
        bst.insert('E', 1);
        bst.insert('A', 2);
        bst.insert('R', 3);
        bst.insert('C', 4);
        bst.insert('H', 5);
        bst.insert('X', 6);
        bst.insert('M', 7);
        bst.insert('P', 8);
        bst.insert('L', 9);
        bst.print();
        bst.search('H').ifPresent(StdOut::println); // 5
        bst.search('C').ifPresent(StdOut::println); // 4
    }

    public Optional<V> search(K key) {
        return Optional.ofNullable(root)
                .flatMap(n -> n.get(key));
    }

    public void insert(K key, V val) {
        if (root != null) {
            Node n = root.insert(key, val);
            if (n != null)
                root = n;
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
            kvs = new LinkedList<>();
            kvs.add(kv);
            links = new LinkedList<>();
        }

        private Node(K k, V v) {
            this(new KeyValue(k, v));
        }

        private Node(KeyValue kv, Node left, Node right) {
            this(kv);
            if (left != null)
                links.add(left);
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
                    insertInto(RIGHT, key, val);
                }
            } else {
                kvs.get(0).value = val;
            }
            if (isThreeNode()) {
                Node left = new Node(kvs.get(0), getLink(LEFT), getLink(MIDDLE));
                Node right = new Node(kvs.get(2), getLink(RIGHT), getLink(TEMP));
                return new Node(kvs.get(1), left, right);
            }
            return null;
        }

        private void insertInto(int pos, K key, V val) {
            Node n = getLink(pos);
            if (n != null) {
                Node newNode = n.insert(key, val);
                if (newNode != null) {
                    removeLink(pos);
                    merge(newNode);
                }
            } else {
                addKeyValue(new KeyValue(key, val));
            }
        }

        private void removeLink(int pos) {
            if (pos == LEFT && !links.isEmpty())
                links.remove(LEFT);
            else if (pos == MIDDLE && links.size() == 3)
                links.remove(MIDDLE);
            else if (pos == RIGHT && links.size() == 2)
                links.remove(1);
            else if (pos == RIGHT && links.size() > 2)
                links.remove(RIGHT);
        }

        private boolean isThreeNode() {
            return kvs.size() > 2;
        }

        private void merge(Node node) {
            if (node != null) {
                HashSet<KeyValue> keyValSet = new HashSet<>(kvs);
                keyValSet.addAll(node.kvs);
                kvs = new LinkedList<>(keyValSet);

                HashSet<Node> nodeSet = new HashSet<>(links);
                nodeSet.addAll(node.links);
                links = new LinkedList<>(nodeSet);

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
            else if (pos == MIDDLE && links.size() > 2)
                return links.get(MIDDLE);
            else if (pos == RIGHT && links.size() == 2)
                return links.get(1);
            else if (pos == RIGHT && links.size() > 2)
                return links.get(RIGHT);
            else if (pos == TEMP && links.size() > 3)
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return kvs.equals(node.kvs);
        }

        @Override
        public int hashCode() {
            return kvs.hashCode();
        }
    }

    private class KeyValue {
        private final K key;
        private V value;

        private KeyValue(K k, V v) {
            key = k;
            value = v;
        }

        @Override
        public String toString() {
            return key.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyValue keyValue = (KeyValue) o;
            return key.equals(keyValue.key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }
}