package _4_graphs._1_undirected_graphs;

import edu.princeton.cs.algs4.Queue;

class BFSPaths {
    private final boolean[] visited;
    private final int[] edgeTo;

    BFSPaths(Graph g, int source) {
        visited = new boolean[g.getV()];
        edgeTo = new int[g.getV()];
        bfs(g, source);
    }

    private void bfs(Graph g, int s) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (Integer w : g.adj(v)) {
                if (!visited[w]) {
                    edgeTo[w] = v;
                    visited[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    boolean hasPathTo(int w) {
        return visited[w];
    }
}
