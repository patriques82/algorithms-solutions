package fundamentals.bags_queues_and_stacks;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by patriknygren on 2017-04-11.
 */
public class Ex_1_3_25 {

    public static void main(String[] args) {
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n4 = new Node<>(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        LinkedList<Integer> list = new LinkedList<>(n1);
        StdOut.println(list.toString()); // 1 -> 2 -> 3 -> 4
        insertAfter(n1, n3);
        StdOut.println(list.toString()); // 1 -> 3 -> 4
    }

    private static <E> void insertAfter(Node<E> n1, Node<E> n2) {
        n1.next = n2;
    }

}
