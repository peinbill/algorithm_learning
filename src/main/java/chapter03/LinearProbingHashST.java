package chapter03;

import chapter01.Queue;

/**
 * @Classname LinearProbingHashST
 * @Description 线性探测的符号表
 * @Date 2020/11/9 15:39
 * @Created by laohuang
 */
public class LinearProbingHashST<Key,Value> {

    private int N;// 符号表中键值对的综述
    private int M =16;// 线性探测表的大小
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST(){
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int M){
        this.M = M;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    private int hash(Key key){
        return (key.hashCode()&0x7fffffff)%M;
    }

    private void resize(int n){
        LinearProbingHashST<Key,Value> t;
        t = new LinearProbingHashST<>(n);
        for(int i=0;i<M;i++){
            if(keys[i] != null){
                t.put(keys[i],values[i]);
            }
        }
        keys = t.keys;
        values = t.values;
        M = t.M;
    }

    public void put(Key key,Value value){
        if(N>=M/2){
            resize(2*M);
        }
        int i;
        for(i=hash(key);keys!=null;i=(i+1)/M){
            if(keys[i].equals(key)){
                values[i] = value;
                return;
            }
        }
        keys[i]=key;
        values[i]=value;
        N++;

    }

    public Value get(Key key){
        for(int i=hash(key);keys!=null;i=(i+1)/M){
            if(keys[i].equals(key)){
                return values[i];
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }

        // delete key and associated value
        keys[i] = null;
        values[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % M;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key   keyToRehash = keys[i];
            Value valToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % M;
        }

        N--;
        // halves size of array if it's 12.5% full or less
        if (N > 0 && N <= M/8){
            resize(M/2);
        }
    }

    public int size() {
        return N;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }
}
