package _4_graphs._3_minimum_spanning_trees;

import edu.princeton.cs.algs4.Bag;

import java.util.HashSet;
import java.util.Set;

public class EdgeWeightedGraph {
    private int v;
    private int e;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int v) {
        this.v = v;
        this.e = 0;
        adj = (Bag<Edge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public int getV() {
        return v;
    }

    public int getE() {
        return e;
    }

    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        e++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Set<Edge> edges = new HashSet<>();
        for (int i = 0; i < getV(); i++) {
            for (Edge e : adj(i)) {
                if (!edges.contains(e)) {
                    edges.add(e);
                }
            }
        }
        return edges;
    }

}
