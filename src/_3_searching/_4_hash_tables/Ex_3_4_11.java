package _3_searching._4_hash_tables;

import edu.princeton.cs.algs4.StdOut;

/**
 Give the contents of a linear-probing hash table that results when you insert the
 keys E A S Y Q U T I O N in that order into an initially empty table of initial size M
 = 4 that is expanded with doubling whenever half full. Use the hash function 11 k % M
 to transform the kth letter of the alphabet into a table index.
 */
public class Ex_3_4_11 {

    public static void main(String[] args) {
        LinearProbingHashST<Character, Integer> st = new LinearProbingHashST<>(4);
        st.put('E', 0);
        st.put('A', 1);
        st.put('S', 2);
        st.put('Y', 3);
        st.put('Q', 4);
        st.put('U', 5);
        st.put('T', 6);
        st.put('I', 7);
        st.put('O', 8);
        st.put('N', 9);
        st.print(); // _ _ _ I _ O _ U _ _ _ A _ _ _ _ _ S _ Y _ _ _ E _ _ N Q T _ _ _
    }

}
