package sorting.priority_queues;

import edu.princeton.cs.algs4.StdOut;

/**
 * Heap: A binary tree is heap-ordered if the key in each node is larger than or
 * equal to the keys in that node’s two children (if any).
 */
public class PriorityQueue<Key extends Comparable<Key>> extends Ex_2_4_3.ArrayPQ<Key> {

    public static void main(String[] args) {
        StdOut.println(PriorityQueue.class.getSimpleName() + ":");
        PriorityQueue<Integer> pq = new PriorityQueue<>(5);
        pq.insert(8);
        pq.insert(3);
        pq.insert(6);
        pq.insert(2);
        pq.insert(7);
        while (!pq.isEmpty()) {
            StdOut.print(pq.removeMax() + " "); // 8 7 6 3 2
        }
    }


    PriorityQueue(int max) {
        super(max + 1);
    }

    void insert(Key k) {
        if (!isFull()) {
            pq[++count] = k;
            swim(count);
        }
    }

    Key removeMax() {
        if (!isEmpty()) {
            Key max = pq[1];
            swap(1, count--);
            pq[count + 1] = null;
            sink(1);
            return max;
        }
        return null;
    }

    private void sink(int k) {
        while (2 * k <= count) {
            int max = k * 2;
            if (max + 1 <= count && less(max, max + 1))
                max++;
            if (!less(k, max)) {
                break;
            }
            swap(k, max);
            k = max;
        }
    }

    private void swim(int k) {
        while (k / 2 > 0 && less(k / 2, k)) {
            swap(k / 2, k);
            k /= 2;
        }
    }

}

