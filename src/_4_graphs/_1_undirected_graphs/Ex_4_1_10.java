package _4_graphs._1_undirected_graphs;

import edu.princeton.cs.algs4.StdOut;

/**
 Prove that every connected graph has a vertex whose removal (including all
 adjacent edges) will not disconnect the graph, and write a DFS method that finds such
 a vertex. Hint : Consider a vertex whose adjacent vertices are all marked.

 Proof by contradiction:
 If a path between two vertices always goes through a specific vertex the removal of this
 would disconnect the graph. If all vertices are part of such a path we could remove any
 vertex to disconnect the graph, this would mean that the two disconnected vertices has
 no other path to each other, but since they are part of paths between other vertices that
 necessary to connect the remaining vertices the remaining vertices must all be connected.
 */
public class Ex_4_1_10 {

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
        VertexFinder finder = new VertexFinder(g);
        StdOut.print(finder.dfs(0)); // 6 (for example)
    }

    private static class VertexFinder {
        private final boolean[] visited;
        private final Graph graph;

        VertexFinder(Graph g) {
            visited = new boolean[g.getV()];
            this.graph = g;
        }

        int dfs(int s) {
            visited[s] = true;
            if (allVisited(graph.adj(s))) {
                return s;
            } else {
                for (Integer v : graph.adj(s)) {
                    if (!visited[v]) {
                        return dfs(v);
                    }
                }
            }
            return -1; // no such vertex
        }

        private boolean allVisited(Iterable<Integer> adj) {
            for (int v : adj) {
                if (!visited[v])
                    return false;
            }
            return true;
        }
    }
}
