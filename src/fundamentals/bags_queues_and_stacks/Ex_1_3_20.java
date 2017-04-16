package fundamentals.bags_queues_and_stacks;

import edu.princeton.cs.algs4.StdOut;


/**
 * Created by patriknygren on 2017-04-09.
 */
public class Ex_1_3_20 {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(1, 2, 3);
        StdOut.println(list.toString()); // 1 -> 2 -> 3
        delete(1, list);
        StdOut.println(list.toString()); // 1 -> 3
    }

    private static <E> void delete(int k, LinkedList<E> list) {
        if (k < 0) {
            throw new RuntimeException("Negative index!");
        } else if(k == 0) {
            if(list.last == list.first) {
                list.first = null;
                list.last = null;
            } else {
                list.first = list.first.next;
            }
        } else {
            Node<E> curr = list.first;
            int i = 0;
            while (curr.next != null) {
                if (i == k - 1) {
                    curr.next = curr.next.next;
                    break;
                }
                i++;
                curr.next = curr.next;
            }
        }
    }

}
