package chapter03;


import javafx.scene.shape.VLineTo;

/**
 * @Classname BinarySearchST
 * @Description TODO
 * @Date 2020/11/3 11:01
 * @Created by laohuang
 */
public class BinarySearchST<Key extends Comparable<Key>,Value>{

    private Key[] keys;
    private Value[] values;
    private int N;
    public BinarySearchST(int capacity){
        keys = (Key[]) new Object[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int rank(Key key){
        int lo=0,hi=N-1;
        while (lo<=hi){
            int mid = lo+(hi-lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp<0){
                hi = mid-1;
            }else if(cmp>0){
                lo =mid+1;
            }else {
                return mid;
            }
        }
        return lo;
    }

    public Value get(Key key){
        if(isEmpty()){
            return null;
        }
        int i = rank(key);
        if(i<N&&keys[i].compareTo(key)==0){
            return values[i];
        }else {
            return null;
        }
    }

    public void put(Key key, Value value){
        int i = rank(key);
        if(i<N&&keys[i].compareTo(key)==0){
            values[i]=value;
            return;
        }
        for (int j=N;j>i;j--){
            keys[j] = keys[j-1];
            values[j]=values[j-1];
        }
        keys[i]=key;
        values[i]=value;
        N++;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (isEmpty()) return;

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == N || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < N-1; j++)  {
            keys[j] = keys[j+1];
            values[j] = values[j+1];
        }

        N--;
        keys[N] = null;  // to avoid loitering
        values[N] = null;


    }
}
