package _4_graphs._4_shortest_paths;

public interface ShortestPath {

    double distTo(int v); // distance from s to v, ∞ if no path

    boolean hasPathTo(int v); // path from s to v?

    Iterable<DirectedEdge> pathTo(int v);

}
