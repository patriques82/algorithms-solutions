package _3_searching._4_hash_tables;

import edu.princeton.cs.algs4.SequentialSearchST;

public class SeparateChainingHashST<K, V> {
    private SequentialSearchST<K, V>[] arr;
    private int cap;

    public SeparateChainingHashST(int cap) {
        this.cap = cap;
        this.arr = (SequentialSearchST<K, V>[]) new SequentialSearchST[cap];
    }

    public void put(K key, V val) {
        int h = hash(key);
        if (arr[h] == null)
            arr[h] = new SequentialSearchST<>();
        arr[h].put(key, val);
    }

    public V get(K key) {
        return arr[hash(key)].get(key);
    }

    private int hash(K key) {
        return hash(key, 11);
    }

    private int hash(K key, int a) {
        return a * (key.hashCode() & 0x7fffffff) % cap;
    }

}
