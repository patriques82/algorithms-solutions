package _4_graphs._3_minimum_spanning_trees;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

public class KruskalMST {
    private MinPQ<Edge> edges;
    private UF uf;
    private Queue<Edge> mst;

    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(8);
        g.addEdge(new Edge(4, 5, 0.35));
        g.addEdge(new Edge(4, 7, 0.37));
        g.addEdge(new Edge(5, 7, 0.28));
        g.addEdge(new Edge(0, 7, 0.16));
        g.addEdge(new Edge(1, 5, 0.32));
        g.addEdge(new Edge(0, 4, 0.38));
        g.addEdge(new Edge(2, 3, 0.17));
        g.addEdge(new Edge(1, 7, 0.19));
        g.addEdge(new Edge(0, 2, 0.26));
        g.addEdge(new Edge(1, 2, 0.36));
        g.addEdge(new Edge(1, 3, 0.29));
        g.addEdge(new Edge(2, 7, 0.34));
        g.addEdge(new Edge(6, 2, 0.40));
        g.addEdge(new Edge(3, 6, 0.52));
        g.addEdge(new Edge(6, 0, 0.58));
        g.addEdge(new Edge(6, 4, 0.93));
        KruskalMST mst = new KruskalMST(g);
        for (Edge e : mst.getEdges()) {
            StdOut.println(e);
        }
    }

    public KruskalMST(EdgeWeightedGraph g) {
        edges = new MinPQ<>();
        uf = new UF(g.getV());
        mst = new Queue<>();
        g.edges().forEach(e -> edges.insert(e));
        while (!edges.isEmpty() && mst.size() < g.getV() - 1) {
            Edge e = edges.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) { // no cycle?
                mst.enqueue(e);
                uf.union(v, w);
            }
        }
    }

    public Iterable<Edge> getEdges() {
        return mst;
    }
}
