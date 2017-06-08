package _4_graphs._2_directed_graphs;

import edu.princeton.cs.algs4.Queue;

import java.util.Stack;

public class DFOrder {
    private boolean[] visited;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost; // also topological sort (order)

    DFOrder(Digraph g) {
        visited = new boolean[g.getV()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        for (int i = 0; i < g.getV(); i++) {
            if (!visited[i])
                dfs(g, i);
        }
    }

    private void dfs(Digraph g, int v) {
        pre.enqueue(v);
        visited[v] = true;
        for (int i : g.adj(v)) {
            if (!visited[i])
                dfs(g, i);
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Queue<Integer> getPreOrder() {
        return pre;
    }

    public Queue<Integer> getPostOrder() {
        return post;
    }

    public Stack<Integer> getReversePostOrder() {
        return reversePost;
    }

}
