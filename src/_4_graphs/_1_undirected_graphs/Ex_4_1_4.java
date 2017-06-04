package _4_graphs._1_undirected_graphs;

import edu.princeton.cs.algs4.StdOut;

/**
 Add a method hasEdge() to Graph which takes two int
 arguments v and w and returns true if the graph has an edge v-w,
 false otherwise.
 */
public class Ex_4_1_4 {

    public static void main(String[] args) {
        ExtendedGraph g = new ExtendedGraph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 6);
        g.addEdge(0, 5);
        g.addEdge(5, 3);
        g.addEdge(5, 4);
        g.addEdge(4, 3);
        g.addEdge(4, 6);
        StdOut.println(g.hasEdge(6, 0)); // true
        StdOut.println(g.hasEdge(6, 1)); // false
    }

    private static class ExtendedGraph extends Graph {

        ExtendedGraph(int v) {
            super(v);
        }

        boolean hasEdge(int v, int w) {
            for (Integer i : adj(v)) {
                if (i == w)
                    return true;
            }
            return false;
        }
    }
}
