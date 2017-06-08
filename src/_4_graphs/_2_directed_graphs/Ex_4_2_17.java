package _4_graphs._2_directed_graphs;

import edu.princeton.cs.algs4.StdOut;

/**
 True or false: The reverse postorder of a graph’s reverse is the same as the postorder
 of the graph.
 */
public class Ex_4_2_17 {

    public static void main(String[] args) {
        Digraph g = new Digraph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 5);
        g.addEdge(0, 6);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 5);
        g.addEdge(5, 4);
        g.addEdge(6, 4);
        DFOrder order = new DFOrder(g);
        DFOrder reverse = new DFOrder(g.reverse());

        for (int v : reverse.getReversePostOrder()) {
            StdOut.print(v); // 2013654
        }
        StdOut.println();
        for (int v : order.getPostOrder()) {
            StdOut.print(v); // 4651032
        }
    }
}

/**
 * Answer: No
 */
