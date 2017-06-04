package _4_graphs._1_undirected_graphs;

import edu.princeton.cs.algs4.Bag;

public class Graph {

    private final int V;
    protected int E;
    protected Bag<Integer>[] adj;

    Graph(int v) {
        V = v;
        E = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Bag<>();
        }
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    Iterable<Integer> adj(int v) {
        return adj[v];
    }

}
