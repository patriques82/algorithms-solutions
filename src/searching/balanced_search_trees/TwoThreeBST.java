package searching.balanced_search_trees;

import com.sun.istack.internal.Nullable;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class TwoThreeBST<K extends Comparable<K>, V> {
    private Node root;

    public static void main(String[] args) {
        TwoThreeBST<Integer, Character> bst = new TwoThreeBST<>();
        bst.insert(1, 'A');
        bst.insert(2, 'B');
        bst.insert(3, 'C');
        bst.insert(4, 'D');
        bst.insert(5, 'E');
        bst.insert(6, 'F');
        bst.insert(7, 'G');
        bst.insert(8, 'H');
        bst.insert(9, 'I');
        bst.print();
        bst.search(5).ifPresent(StdOut::println); // E
        bst.search(4).ifPresent(StdOut::println); // D
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

        @Nullable
        private Optional<V> get(K key) {
            KeyValue kv1 = getFirst();
            int cmp1 = key.compareTo(kv1.key);
            if (cmp1 < 0) {
                return hasLeftLink() ? getLeftLink().get(key) : null;
            } else if (cmp1 > 0) {
                if (kvs.size() == 2) {
                    KeyValue kv2 = getSecond();
                    int cmp2 = key.compareTo(kv2.key);
                    if (cmp2 < 0) {
                        return hasMiddleLink() ? getMiddleLink().get(key) : null;
                    } else if (cmp2 > 0) {
                        return hasRightLink() ? getRightLink().get(key) : null;
                    } else {
                        return Optional.of(kv2.value);
                    }
                } else {
                    return hasRightLink() ? getRightLink().get(key) : null;
                }
            } else {
                return Optional.of(kv1.value);
            }
        }

        private Node insert(K key, V val) {
            int cmp1 = key.compareTo(getFirst().key);
            if (cmp1 < 0) {
                if (!hasLeftLink())
                    addKeyValue(new KeyValue(key, val));
                else
                    getLeftLink().merge(getLeftLink().insert(key, val));
            } else if (cmp1 > 0) {
                if (kvs.size() == 2) {
                    int cmp2 = key.compareTo(getSecond().key);
                    if (cmp2 < 0) {
                        if (!hasMiddleLink())
                            addKeyValue(new KeyValue(key, val));
                        else
                            getMiddleLink().merge(getMiddleLink().insert(key, val));
                    } else if (cmp2 > 0) {
                        if (!hasRightLink())
                            addKeyValue(new KeyValue(key, val));
                        else
                            getRightLink().merge(getRightLink().insert(key, val));
                    } else {
                        kvs.get(1).value = val;
                    }
                } else {
                    addKeyValue(new KeyValue(key, val));
                }
            } else {
                kvs.get(0).value = val;
            }
            if (kvs.size() > 2) {
                Node left = new Node(kvs.get(0), getLeftLink(), getMiddleLink());
                Node right = new Node(kvs.get(2), getRightLink(), null);
                return new Node(kvs.get(1), left, right);
            }
            return null;
        }

        private void merge(Node node) {
            if (node != null) {
                kvs = node.kvs;
                links = node.links;
                sort();
            }
        }

        private KeyValue getFirst() {
            return kvs.get(0);
        }

        private KeyValue getSecond() {
            return kvs.get(1);
        }

        private void addKeyValue(KeyValue kv) {
            kvs.add(kv);
            sortKvs();
        }

        private void print() {
            StdOut.print("{");
            kvs.forEach(StdOut::print);
            links.forEach(Node::print);
            StdOut.print("}");
        }

        private int getHeight() {
            Optional<Integer> max = links.stream()
                    .map(Node::getHeight)
                    .max(Integer::compareTo);
            return max.orElse(0) + 1;
        }

        private boolean hasLeftLink() {
            return !links.isEmpty();
        }

        private Node getLeftLink() {
            return hasLeftLink() ? links.get(0) : null;
        }

        private boolean hasMiddleLink() {
            return links.size() > 2;
        }

        private Node getMiddleLink() {
            return hasMiddleLink() ? links.get(1) : null;
        }

        private boolean hasRightLink() {
            return links.size() > 1;
        }

        private Node getRightLink() {
            return hasMiddleLink() ? links.get(2) : hasRightLink() ? links.get(1) : null;
        }

        private void sort() {
            sortKvs();
            sortNodes();
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
                    if (minCmp < 0 && maxCmp < 0) {
                        return -1;
                    } else if (minCmp > 0 && maxCmp > 0) {
                        return 1;
                    } else {
                        return Math.abs(maxCmp) - Math.abs(minCmp);
                    }
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