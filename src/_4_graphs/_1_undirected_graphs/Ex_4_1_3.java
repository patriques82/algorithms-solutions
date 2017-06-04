package _4_graphs._1_undirected_graphs;

import edu.princeton.cs.algs4.StdOut;

/**
 Create a copy constructor for Graph that takes as input a
 graph G and creates and initializes a new copy of the graph. Any
 changes a client makes to G should not affect the newly created
 graph.
 */
public class Ex_4_1_3 {

    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 6);
        g.addEdge(0, 5);
        g.addEdge(5, 3);
        g.addEdge(5, 4);
        g.addEdge(4, 3);
        g.addEdge(4, 6);
        ExtendedGraph eg = new ExtendedGraph(g);
        g.addEdge(4, 2);
        for (Integer i : g.adj(4)) {
            StdOut.print(i); // 2 6 3 5
        }
        StdOut.println();
        for (Integer i : eg.adj(4)) {
            StdOut.print(i); // 6 3 5
        }

    }

    private static class ExtendedGraph extends Graph {

        ExtendedGraph(Graph g) {
            super(g.getV());
            for (int v = 0; v < g.getV(); v++) {
                for (Integer w : g.adj(v)) {
                    adj[v].add(w);
                }
            }
        }
    }
}
