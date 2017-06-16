package _4_graphs._4_shortest_paths;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * Use BellmanFord when graph has
 * - cycles
 * - negative weights
 */
public class BellmanFordSP implements ShortestPath {

    private final double[] distTo;
    private final DirectedEdge[] edgeTo;
    private final boolean[] onQ;
    private final Queue<Integer> queue;
    // private int cost;

    public BellmanFordSP(EdgeWeightedDigraph graph, int s) {
        distTo = new double[graph.getV()];
        edgeTo = new DirectedEdge[graph.getV()];
        onQ = new boolean[graph.getV()];
        queue = new Queue<>();
        distTo[s] = 0.0;
        for (int v = 0; v < graph.getV(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        /*
        Algorithm: The Bellman-Ford algorithm takes time proportional
        to EV and extra space proportional to V.
        */
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty()) { // && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(graph, v);
        }
    }

    private void relax(EdgeWeightedDigraph graph, int v) {
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            /*
            OBS: Not implemented to keep the idea simple.

            if (cost++ % graph.getV() == 0)
                findNegativeCycle();
            */
        }
    }

    /*
    private void findNegativeCycle()
    public boolean hasNegativeCycle()
    */

    @Override
    public double distTo(int v) {
        return distTo[v];
    }

    @Override
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    @Override
    public Iterable<DirectedEdge> pathTo(int v) {
        Stack<DirectedEdge> path = new Stack<>();
        DirectedEdge e = edgeTo[v];
        while (e != null) {
            path.push(e);
            e = edgeTo[e.from()];
        }
        return path;
    }
}
