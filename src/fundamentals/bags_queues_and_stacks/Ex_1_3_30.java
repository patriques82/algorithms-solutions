package fundamentals.bags_queues_and_stacks;

import edu.princeton.cs.algs4.StdOut;

/**
 * Write a function that takes the first Node in a linked list as argument and (destructively)
 * reverses the list, returning the first Node in the result.
 */
public class Ex_1_3_30 {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(1,2,3,4);
        StdOut.println(list); // 1 -> 2 -> 3 -> 4
        Node<Integer> node = reverse(list.first);
        StdOut.println(node); // 4 -> 3 -> 2 -> 1
    }

    private static <E> Node<E> reverse(Node<E> node) {
        return iterableReverse(node);
    }

    private static <E> Node<E> iterableReverse(Node<E> node) {
        Node<E> curr = node.next;
        Node<E> prev = node;
        prev.next = null;
        while(curr != null) {
            Node<E> temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    private static <E> Node<E> recursiveReverse(Node<E> node) {
        if(node.next == null) {
            return node;
        } else {
            Node<E> second = node.next;
            Node<E> reversed = recursiveReverse(second);
            node.next = null;
            second.next = node;
            return reversed;
        }
    }

}
