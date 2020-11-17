package chapter02;

import java.util.Iterator;

/**
 * @Classname MinPQ
 * @Description TODO
 * @Date 2020/11/15 10:05
 * @Created by laohuang
 */
public class MinPQ<Key extends Comparable<Key>> implements Iterable<Key> {


    private Key[] pq;
    private int N = 0;
    public MinPQ() {
        this(0);
    }
    public MinPQ(int maxN) {
        pq = (Key[]) new Object[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }


    private boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    public void insert(Key key) {
        pq[++N] = key;
        swim(N);
    }


    public Key delMin() {
        Key min = pq[1];
        exch(1, N--);
        sink(1);
        pq[N+1] = null;     // to avoid loiterig and help with garbage collection
        return min;
    }

    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        // create a new pq
        private MinPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new MinPQ<Key>(size());
            for (int i = 1; i <= N; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Key next() {
            return copy.delMin();
        }
    }
}
