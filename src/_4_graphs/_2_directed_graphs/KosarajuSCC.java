package _4_graphs._2_directed_graphs;

/**
 * Kosaraju Strong Connected Components Algorithm
 *
 * Finds all STRONGLY connected components in a graph
 *

 Pseudoccode:
 ■  Given a digraph G, use DepthFirstOrder to compute the reverse postorder of
    its reverse.
 ■  Run standard DFS on G, but consider the unmarked vertices in the order just
    computed instead of the standard numerical order.
 ■  All vertices reached on a call to the recursive dfs() from the constructor are in a
    strong component (!), so identify them as in CC.

 * Wikipedia def (Connected components):
 * "In graph theory, a connected component (or just component)
 * of an undirected graph is a subgraph in which any two vertices
 * are connected to each other by paths, and which is connected
 * to no additional vertices in the supergraph."
 *
 * Wikipedia def (Strong component):
 * A pair of vertices u and v are said to be strongly connected to
 * each other if there is a path in each direction between them.
 * A directed graph is called strongly connected if there is a
 * path in each direction between each pair of vertices of the graph
 */
public class KosarajuSCC {
    private boolean[] visited;
    private int[] id;
    private int count;

    KosarajuSCC(Digraph g) {
        visited = new boolean[g.getV()];
        id = new int[g.getV()];
        count = 0;

        // this is the whole algorithm
        DFOrder order = new DFOrder(g.reverse());
        for (int v : order.getReversePostOrder()) {
            if (!visited[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    boolean stronglyConnected(int v, int w) {  // are v and w strongly connected?
        return id[v] == id[w];
    }

    int count() { // number of strong components
        return count;
    }

    int id(int v) { // component identifier for v (between 0 and count()-1)
        return id[v];
    }

    private void dfs(Digraph g, int v) {
        visited[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!visited[w])
                dfs(g, w);
        }
    }
}
