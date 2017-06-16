package _4_graphs._4_shortest_paths;

import edu.princeton.cs.algs4.Topological;

/**
 * Use Acyclic (topological sort) when graph has
 * - no cycles
 * - no negative weights
 */
public class AcyclicSP {
    private edu.princeton.cs.algs4.DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(edu.princeton.cs.algs4.EdgeWeightedDigraph graph, int source) {
        edgeTo = new edu.princeton.cs.algs4.DirectedEdge[graph.V()];
        distTo = new double[graph.V()];
        distTo[source] = 0.0;
        for (int v = 0; v < graph.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        /*
        Algorithm: By relaxing vertices in t opological order, we can solve the single source
        shortest-paths problem for edge-weighted DAGs in time proportional to
        E + V.
         */
        Topological top = new Topological(graph);
        for (int v : top.order())
            relax(graph, v);
    }

    private void relax(edu.princeton.cs.algs4.EdgeWeightedDigraph graph, int v) {
        for (edu.princeton.cs.algs4.DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    /*
    Same as Dijkstra and BellmanFord
    public double distTo(int v)

    public boolean hasPathTo(int v)

    public Iterable<Edge> pathTo(int v)
    */
}
