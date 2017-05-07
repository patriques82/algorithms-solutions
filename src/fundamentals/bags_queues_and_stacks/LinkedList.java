package fundamentals.bags_queues_and_stacks;

/**
 * Created by patriknygren on 2017-04-09.
 */
public class LinkedList<E> {

    public Node<E> first, last;

    public LinkedList() {
        first = null;
        last = null;
    }

    public LinkedList(Node<E> node) {
        Node<E> curr = node;
        first = curr;
        while(curr.next != null) {
            curr = curr.next;
        }
        last = curr;
    }

    public LinkedList(E el, E ... elems) {
        Node<E> curr = null;
        Node<E> prev;
        first = new Node<>(el);
        if(elems.length == 0) {
            last = first;
        } else {
            prev = first;
            for (E e : elems) {
                curr = new Node<>(e);
                prev.next = curr;
                prev = curr;
            }
            last = curr;
        }
    }

    public void add(E el) {
        if(isEmpty()) {
            first = new Node<>(el);
            last = first;
        } else {
            Node<E> temp = new Node<>(el);
            last.next = temp;
            last = temp;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
       return first.toString();
    }

}
