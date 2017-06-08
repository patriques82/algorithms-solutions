package _4_graphs._2_directed_graphs;

import edu.princeton.cs.algs4.Bag;

public class Digraph {
    private final int V;
    protected int E;
    protected Bag<Integer>[] adj;

    Digraph(int v) {
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
        //adj[w].add(v); this line exist in Graph but not in Digraph
        E++;
    }

    Iterable<Integer> adj(int v) {
        return adj[v];
    }

    Digraph reverse() {
        Digraph g = new Digraph(getV());
        for (int v = 0; v < getV(); v++) {
            for (int w : adj[v]) {
                g.addEdge(w, v);
            }
        }
        return g;
    }
}
