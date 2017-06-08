package _4_graphs._2_directed_graphs;

import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 The indegree of a vertex in a digraph is the number of directed edges that point to
 that vertex. The outdegree of a vertex in a digraph is the number of directed edges that
 emanate from that vertex. No vertex is reachable from a vertex of outdegree 0, which is
 called a sink; a vertex of indegree 0, which is called a source, is not reachable from any
 other vertex. A digraph where self-loops are allowed and every vertex has outdegree 1
 is called a map (a function from the set of integers from 0 to V–1 onto itself). Write a
 program Degrees.java that implements the following API:

 class Degrees
    Degrees(Digraph G) constructor
    int indegree(int v) indegree of v
    int outdegree(int v) outdegree of v
    Iterable<Integer> sources() sources
    Iterable<Integer> sinks() sinks
    boolean isMap() is G a map?
 */
public class Ex_4_2_7 {

    public static void main(String[] args) {
        Digraph g = new Digraph(13);
        g.addEdge(0, 1);
        g.addEdge(0, 5);
        g.addEdge(0, 6);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 5);
        g.addEdge(5, 4);
        g.addEdge(6, 4);
        g.addEdge(6, 9);
        g.addEdge(7, 6);
        g.addEdge(8, 7);
        g.addEdge(9, 10);
        g.addEdge(9, 11);
        g.addEdge(9, 12);
        g.addEdge(11, 12);
        Degrees deg = new Degrees(g);
        StdOut.println(deg.indegree(6));  // 2
        StdOut.println(deg.outdegree(6)); // 2
        StdOut.println(deg.indegree(9));  // 1
        StdOut.println(deg.outdegree(9)); // 3
        StdOut.println(deg.indegree(4));  // 2
        StdOut.println(deg.outdegree(4)); // 0
        StdOut.print("Sources: ");
        for (int v : deg.sources()) {
            StdOut.print(v + " "); // 2 8
        }
        StdOut.print("\nSinks: ");
        for (int v : deg.sinks()) {
            StdOut.print(v + " "); // 1 4 10 12
        }
        StdOut.println("\nIs map: " + deg.isMap()); // false
    }

    private static class Degrees {
        private final Digraph digraph;
        private Iterable<Integer> sources;
        private Iterable<Integer> sinks;

        Degrees(Digraph g) {
            digraph = g;
        }

        int indegree(int v) {
            int count = 0;
            for (int w = 0; w < digraph.getV(); w++) {
                for (int x : digraph.adj(w)) {
                    if (x == v) {
                        count++;
                    }
                }
            }
            return count;
        }

        int outdegree(int v) {
            int count = 0;
            for (int w : digraph.adj(v)) {
                count++;
            }
            return count;
        }

        Iterable<Integer> sources() {
            if (sources == null) {
                Set<Integer> nonSources = new HashSet<>();
                Set<Integer> vertices = new HashSet<>();
                for (int v = 0; v < digraph.getV(); v++) {
                    vertices.add(v);
                    for (int w : digraph.adj(v)) {
                        nonSources.add(w);
                    }
                }
                vertices.removeAll(nonSources);
                sources = vertices;
            }
            return sources;
        }

        Iterable<Integer> sinks() {
            if (sinks == null) {
                Set<Integer> _sinks = new HashSet<>();
                for (int v = 0; v < digraph.getV(); v++) {
                    if (!digraph.adj(v).iterator().hasNext())
                        _sinks.add(v);
                }
                sinks = _sinks;
            }
            return sinks;
        }

        boolean isMap() {
            for (int v = 0; v < digraph.getV(); v++) {
                int count = 0;
                for (int w : digraph.adj(v)) {
                    count++;
                }
                if (count != 1)
                    return false;

            }
            return true;
        }
    }

}
