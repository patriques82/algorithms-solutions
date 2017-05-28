package _1_fundamentals._3_bags_queues_and_stacks;

import edu.princeton.cs.algs4.StdOut;

/**
 * Implement a nested class DoubleNode for building doubly-linked lists, where
 * each node contains a reference to the item preceding it and the item following it in the
 * list (null if there is no such item). Then implement static methods for the following
 * tasks: insert at the beginning, insert at the end, remove from the beginning, remove
 * from the end, insert before a given node, insert after a given node, and remove a given
 * node.
 */
public class Ex_1_3_31 {

    public static void main(String[] args) {
        DoubleNode<Integer> n1 = new DoubleNode<>(1);
        DoubleNode<Integer> n2 = new DoubleNode<>(2);
        DoubleNode<Integer> n3 = new DoubleNode<>(3);
        DoubleNode<Integer> n4 = new DoubleNode<>(4);

        n1.next = n2;
        n2.prev = n1;
        n2.next = n3;
        n3.prev = n2;
        n3.next = n4;
        n4.prev = n3;

        StdOut.println(n1); // 1 -> 2 -> 3 -> 4

        DoubleNode<Integer> n0 = new DoubleNode<>(0);
        insertAtBeginning(n0, n1);
        StdOut.println(n0); // 0 -> 1 -> 2 -> 3 -> 4

        DoubleNode<Integer> n5 = new DoubleNode<>(5);
        insertAtEnd(n5, n1);
        StdOut.println(n0); // 0 -> 1 -> 2 -> 3 -> 4 -> 5

        DoubleNode<Integer> n100 = new DoubleNode<>(100);
        insertBefore(n3, n100);
        StdOut.println(n0); // 0 -> 1 -> 2 -> 100 -> 3 -> 4 -> 5

    }

    private static <E> void insertAtBeginning(DoubleNode<E> newNode, DoubleNode<E> node) {
        DoubleNode<E> curr = node;
        while(curr.prev != null) {
            curr = curr.prev;
        }
        newNode.next = curr;
        curr.prev = newNode;
    }

    private static <E> void insertAtEnd(DoubleNode<E> newNode, DoubleNode<E> node) {
        DoubleNode<E> curr = node;
        while(curr.next != null) {
            curr = curr.next;
        }
        newNode.prev = curr;
        curr.next = newNode;
    }

    private static <E> void insertBefore(DoubleNode<E> node, DoubleNode<E> newNode) {
        DoubleNode<E> prev = node.prev;
        node.prev = newNode;
        newNode.next = node;
        newNode.prev = prev;
        prev.next = newNode;
    }

    static class DoubleNode<E> extends Node<E> {
        E elem;
        DoubleNode<E> prev;
        DoubleNode<E> next;

        DoubleNode(E elem) {
            super(elem);
            this.elem = elem;
            this.prev = null;
        }

        @Override
        public String toString() {
            if(this.next == null)
                return elem + "";
            return elem + " -> " + next;
        }

    }

}
