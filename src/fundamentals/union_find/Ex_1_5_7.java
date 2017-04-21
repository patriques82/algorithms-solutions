package fundamentals.union_find;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by patriknygren on 2017-04-21.
 */
public class Ex_1_5_7 {

    public static void main(String[] args) {
        UF quickFindUF = new QuickFindUF(10);
        quickFindUF.union(4, 3);
        quickFindUF.union(3, 8);
        quickFindUF.union(6, 5);
        quickFindUF.union(9, 4);
        quickFindUF.union(2, 1);
        StdOut.println(quickFindUF.connected(8, 9)); // true
        quickFindUF.union(5, 0);
        quickFindUF.union(7, 2);
        quickFindUF.union(6, 1);
        StdOut.println(quickFindUF.connected(1, 0)); // true
        StdOut.println(quickFindUF.connected(6, 7)); // true
        StdOut.println(quickFindUF.connected(0, 9)); // false
        StdOut.println(quickFindUF.count() + "\n");         // 2

        UF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.union(4, 3);
        quickUnionUF.union(3, 8);
        quickUnionUF.union(6, 5);
        quickUnionUF.union(9, 4);
        quickUnionUF.union(2, 1);
        StdOut.println(quickUnionUF.connected(8, 9)); // true
        quickUnionUF.union(5, 0);
        quickUnionUF.union(7, 2);
        quickUnionUF.union(6, 1);
        StdOut.println(quickUnionUF.connected(1, 0)); // true
        StdOut.println(quickUnionUF.connected(6, 7)); // true
        StdOut.println(quickUnionUF.connected(0, 9)); // false
        StdOut.println(quickUnionUF.count());                // 2
    }

    static class QuickFindUF extends UFBase {
        QuickFindUF(int N) {
            super(N);
        }

        @Override
        public int find(int p) {
            return id[p];
        }

        @Override
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            for(int i = 0; i<id.length; i++) {
                if(id[i] == rootP)
                    id[i] = rootQ;
            }
            count--;
        }

    }

    static class QuickUnionUF extends UFBase {
        QuickUnionUF(int N) {
            super(N);
        }

        @Override
        public int find(int p) {
            while(id[p] != p)
                p = id[p];
            return p;
        }

        @Override
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            id[rootP] = rootQ;
            count--;
        }
    }

    static abstract class UFBase implements UF {
        protected final int[] id;
        protected int count;

        UFBase(int N) {
            id = new int[N];
            for(int i = 0; i< id.length; i++) {
                id[i] = i;
            }
            count = N;
        }

        @Override
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        @Override
        public int count() {
            return count;
        }
    }

    // API
    interface UF {
        void union(int p, int q);           // add connection between p and q
        int find(int p);                    // component identifier for p (0 to N-1)
        boolean connected(int p, int q);    // return true if p and q are in the same component
        int count();                        // number of id
    }
}
