package _1_fundamentals._3_bags_queues_and_stacks;

import edu.princeton.cs.algs4.StdOut;

/**
 * Write a Queue implementation that uses a circular linked list, which is the same
 * as a linked list except that no links are null and the value of last.next is first whenever
 * the list is not empty. Keep only one Node instance variable (last).
 */
public class Ex_1_3_29 {

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        StdOut.println(queue.size());    // 4
        StdOut.println(queue.dequeue()); // 1
        StdOut.println(queue.dequeue()); // 2
        StdOut.println(queue.size());    // 2
    }

    public static class Queue<E> {
        private final CircularLinkedList<E> circularList;

        public Queue() {
            circularList = new CircularLinkedList<>();
        }

        public void enqueue(E item) {
            circularList.add(item);
        }

        public E dequeue() {
            if(!isEmpty()) {
                if (circularList.last.next == circularList.last) {
                    E elem = circularList.last.elem;
                    circularList.last = null;
                    return elem;
                } else {
                    E elem = circularList.last.next.elem;
                    circularList.last.next = circularList.last.next.next;
                    return elem;
                }
            }
            return null;
        }

        public boolean isEmpty() {
            return circularList.last == null;
        }

        public int size() {
            int items = 0;
            if(!isEmpty()) {
                Node<E> curr = circularList.last.next;
                do {
                    items++;
                    curr = curr.next;
                } while (curr != circularList.last.next);
            }
            return items;
        }
    }

    private static class CircularLinkedList<E> {
        Node<E> last;

        CircularLinkedList() {
            last = null;
        }

        void add(E el) {
            if(last == null) {
                last = new Node<>(el);
                last.next = last; // invariant: no links are null
            } else {
                Node<E> temp = new Node<>(el);
                temp.next = last.next;
                last.next = temp;
                last = temp;
            }
        }

    }

}
