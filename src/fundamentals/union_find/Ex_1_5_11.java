package fundamentals.union_find;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by patriknygren on 2017-04-22.
 */
public class Ex_1_5_11 {

    public static void main(String[] args) {
        UF weightedQuickFind = new WeightedQuickFind(10);
        weightedQuickFind.union(4, 3);
        weightedQuickFind.union(3, 8);
        weightedQuickFind.union(6, 5);
        weightedQuickFind.union(9, 4);
        weightedQuickFind.union(2, 1);
        StdOut.println(weightedQuickFind.connected(8, 9)); // true
        weightedQuickFind.union(5, 0);
        weightedQuickFind.union(7, 2);
        weightedQuickFind.union(6, 1);
        StdOut.println(weightedQuickFind.connected(1, 0)); // true
        StdOut.println(weightedQuickFind.connected(6, 7)); // true
        StdOut.println(weightedQuickFind.connected(0, 9)); // false
        StdOut.println(weightedQuickFind.count());                // 2
    }

    static class WeightedQuickFind extends UFBase {
        int[] weights;

        WeightedQuickFind(int N) {
            super(N);
            weights = new int[N];
            for (int i = 0; i < N; i++)
                weights[i] = 1;
        }

        @Override
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            int larger = weights[rootP] > weights[rootQ] ? rootP : rootQ;
            int smaller = weights[rootP] > weights[rootQ] ? rootQ : rootP;
            for(int i=0; i<id.length; i++) {
                if(id[i] == smaller)
                    id[i] = larger;
            }
            count--;
            weights[larger] += weights[smaller];
        }

        @Override
        public int find(int p) {
            return id[p];
        }
    }
}

/**
 * Analysis of Weighted Quick-find
 * Before weights the array accesses lied between N + 5 and 2N + 3
 * With the weights 6 array accesses are added.
 */