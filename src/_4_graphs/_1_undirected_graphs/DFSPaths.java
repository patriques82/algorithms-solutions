package _4_graphs._1_undirected_graphs;

class DFSPaths {
    private final boolean[] visited;
    private final int[] edgeTo;

    DFSPaths(Graph g, int source) {
        visited = new boolean[g.getV()];
        edgeTo = new int[g.getV()];
        dfs(g, source);
    }

    private void dfs(Graph g, int s) {
        visited[s] = true;
        for (Integer v : g.adj(s)) {
            if (!visited[v]) {
                edgeTo[v] = s;
                dfs(g, v);
            }
        }
    }

    boolean hasPathTo(int w) {
        return visited[w];
    }
}
