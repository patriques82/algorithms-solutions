package _3_searching._1_symbol_tables;

import edu.princeton.cs.algs4.StdOut;

/**
 Develop a symbol-table implementation ArrayST that uses an (unordered) array
 as the underlying data structure to implement our basic symbol-table API.
 */
public class Ex_3_1_2 {

    public static void main(String[] args) {
        ArrayST<Integer, String> st = new ArrayST<>(5);
        st.put(0, "This");
        st.put(3, "should");
        st.put(1, "symbol");
        st.put(4, "work");
        st.put(2, "table");
        for(int i = 0; i < st.size(); i++) {
            StdOut.print(st.get(i) + " "); // This symbol table should work
        }
    }

    static class ArrayST<Key extends Comparable<Key>, Value> {
        Key[] keys;
        Value[] values;
        int N;
        int capacity;

        ArrayST(int capacity) {
            N = 0;
            this.capacity = capacity;
            keys = (Key[]) new Comparable[capacity];
            values = (Value[]) new Comparable[capacity];
        }

        Value get(Key key) {
            for (int i = 0; i < keys.length; i++) {
                if(keys[i].compareTo(key) == 0) {
                    return values[i];
                }
            }
            return null;
        }

        void put(Key key, Value val) {
            for (int i = 0; i < N; i++) {
                if(keys[i].compareTo(key) == 0) {
                    values[i] = val;
                    N++;
                    return;
                }
            }
            if(N < capacity) {
                keys[N] = key;
                values[N] = val;
                N++;
            } else {
                throw new IndexOutOfBoundsException("Symbol table is full");
            }
        }

        int size() {
            return N;
        }

    }
}
