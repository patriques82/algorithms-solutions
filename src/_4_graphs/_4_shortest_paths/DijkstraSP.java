package _4_graphs._4_shortest_paths;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Use Dijkstra when graph has
 * - cycles
 * - no negative weights
 */
public class DijkstraSP implements ShortestPath {
    private final double[] distTo;
    private final IndexMinPQ<Double> pq;
    private final DirectedEdge[] edgeTo;

    public static void main(String[] args) {
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(8); // no negative weights
        graph.addEdge(new DirectedEdge(4, 5, 0.35));
        graph.addEdge(new DirectedEdge(5, 4, 0.37));
        graph.addEdge(new DirectedEdge(4, 7, 0.35));
        graph.addEdge(new DirectedEdge(5, 7, 0.28));
        graph.addEdge(new DirectedEdge(7, 5, 0.28));
        graph.addEdge(new DirectedEdge(5, 1, 0.32));
        graph.addEdge(new DirectedEdge(0, 4, 0.38));
        graph.addEdge(new DirectedEdge(0, 2, 0.26));
        graph.addEdge(new DirectedEdge(7, 3, 0.39));
        graph.addEdge(new DirectedEdge(1, 3, 0.29));
        graph.addEdge(new DirectedEdge(2, 7, 0.34));
        graph.addEdge(new DirectedEdge(6, 2, 0.40));
        graph.addEdge(new DirectedEdge(3, 6, 0.52));
        graph.addEdge(new DirectedEdge(6, 0, 0.58));
        graph.addEdge(new DirectedEdge(6, 4, 0.93));

        int s = 0;
        ShortestPath sp = new DijkstraSP(graph, 0);
        for (int t = 0; t < graph.getV(); t++) {
            StdOut.print(s + " to " + t);
            StdOut.printf(" (%4.2f): ", sp.distTo(t));
            if (sp.hasPathTo(t)) {
                for (DirectedEdge e : sp.pathTo(t))
                    StdOut.print(e + " ");
                StdOut.println();
            }
        }
    }

    public DijkstraSP(EdgeWeightedDigraph graph, int source) {
        pq = new IndexMinPQ<>(graph.getV());
        distTo = new double[graph.getV()];
        edgeTo = new DirectedEdge[graph.getV()];
        distTo[source] = 0.0;
        for (int i = 1; i < graph.getV(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        /*
        Algorithm: Dijkstra’s algorithm uses extra space proportional to V
        and time proportional to E log V (in the worst case) to compute the SPT rooted at
        a given source in an edge-weighted digraph with E edges and V vertices.
        (Similar to Prims alg).
         */
        pq.insert(source, 0.0);
        while (!pq.isEmpty()) {
            relax(graph, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph graph, int v) {
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;

                if (pq.contains(w))
                    pq.changeKey(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }

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
