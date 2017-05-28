package _3_searching._4_hash_tables;

import edu.princeton.cs.algs4.StdOut;

public class LinearProbingHashST<K, V> {
    private int N;
    private int cap;
    private V[] values;
    private K[] keys;

    LinearProbingHashST(int cap) {
        this.N = 0;
        this.cap = cap;
        values = (V[]) new Object[cap];
        keys = (K[]) new Object[cap];
    }

    public void put(K key, V val) {
        if (N >= cap / 2)
            resize(cap*2);
        int h;
        for (h = hash(key); keys[h] != null; h = (h + 1) % cap) {
            if (keys[h].equals(key)) {
                values[h] = val;
                return;
            }
        }
        keys[h] = key;
        values[h] = val;
        N++;
    }

    public V get(K key) {
        int start = hash(key);
        for (int h = start; keys[h] != null; h = (h + 1) % cap) {
            if (keys[h].equals(key))
                return values[h];
            if (h == start - 1)
                break;
        }
        return null;
    }

    public void print() {
        for (int i = 0; i < cap; i++) {
            if (keys[i] == null)
                StdOut.print("_ ");
            else
                StdOut.print(keys[i] + " ");
        }
    }

    private void resize(int newCap) {
        LinearProbingHashST st = new LinearProbingHashST(newCap);
        for (int i = 0; i < this.cap; i++) {
            if (keys[i] != null)
                st.put(keys[i], values[i]);
        }
        cap = newCap;
        values = (V[]) st.values;
        keys = (K[]) st.keys;
    }

    private int hash(K key) {
        return (11 * key.hashCode()) % cap;
    }

}
