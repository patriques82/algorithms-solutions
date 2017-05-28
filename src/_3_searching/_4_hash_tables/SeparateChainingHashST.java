package _3_searching._4_hash_tables;


public class SeparateChainingHashST<K, V> {
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
