package chapter02;

/**
 * @Classname IndexMinPQ
 * @Description TODO
 * @Date 2020/11/15 16:35
 * @Created by laohuang
 */
public class IndexMinPQ<Key extends Comparable<Key>> {

    private int maxN;
    private int n;
    private int[] pq;
    private int[] qp;
    private Key[] keys;



    public IndexMinPQ(int maxN){
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Object[maxN+1];
        pq = new int[maxN+1];
        qp = new int[maxN+1];
        for(int i=0;i<=maxN;i++){
            qp[i] = -1;
        }
    }
    public void change(int i, Key key) {
        changeKey(i, key);
    }
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void validateIndex(int i) {
        if (i < 0) throw new IllegalArgumentException("index is negative: " + i);
        if (i >= maxN) throw new IllegalArgumentException("index >= capacity: " + i);
    }

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public boolean isEmpty(){
        return n==0;
    }

    public boolean contain(int i){
        validateIndex(i);
        return qp[i] != -1;
    }

    public int size(){
        return n;
    }

    public void insert(int i,Key key){
        validateIndex(i);
        if(contain(i)){
            throw new IllegalArgumentException("index is already in the priority queue");
        }
        n++;
        qp[i]=n;
        pq[n] = i;
        keys[i]=key;
        swim(n);
    }

    public int minIndex(){
        if(n==0){
            throw new IllegalArgumentException("Priority queue underflow");
        }
        return pq[1];
    }

    public Key minKey(){
        if(n==0){
            throw new IllegalArgumentException("Priority queue underflow");
        }
        return keys[pq[1]];
    }

    public int delMin(){
        if(n==0){
            throw new IllegalArgumentException("Priority queue underflow");
        }
        int max= pq[1];

        exch(1,n--);
        sink(1);

        assert pq[n+1]==max;
        qp[max]=-1;
        keys[max] = null;
        pq[n+1]=-1;
        return max;
    }

    public Key keyOf(int i){
        validateIndex(i);
        if(!contain(i)){
            throw new IllegalArgumentException("index is not in the priority queue");
        }else return keys[i];

    }

    public void changeKey(int i, Key key) {
        validateIndex(i);
        if (!contain(i)) throw new IllegalArgumentException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    public void increaseKey(int i, Key key) {
        validateIndex(i);
        if (!contain(i)) throw new IllegalArgumentException("index is not in the priority queue");
        if (keys[i].compareTo(key) == 0)
            throw new IllegalArgumentException("Calling increaseKey() with a key equal to the key in the priority queue");
        if (keys[i].compareTo(key) > 0)
            throw new IllegalArgumentException("Calling increaseKey() with a key that is strictly less than the key in the priority queue");

        keys[i] = key;
        sink(qp[i]);
    }

    public void decreaseKey(int i, Key key) {
        validateIndex(i);
        if (!contain(i)) throw new IllegalArgumentException("index is not in the priority queue");
        if (keys[i].compareTo(key) == 0)
            throw new IllegalArgumentException("Calling decreaseKey() with a key equal to the key in the priority queue");
        if (keys[i].compareTo(key) < 0)
            throw new IllegalArgumentException("Calling decreaseKey() with a key that is strictly greater than the key in the priority queue");
        keys[i] = key;
        swim(qp[i]);
    }

    public void delete(int i) {
        validateIndex(i);
        if (!contain(i)) throw new IllegalArgumentException("index is not in the priority queue");
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }
}
