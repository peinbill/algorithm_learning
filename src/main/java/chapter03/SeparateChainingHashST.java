package chapter03;

/**
 * @Classname SeparateChainingHashST
 * @Description 基于拉链法的散列表
 * @Date 2020/11/9 10:35
 * @Created by laohuang
 */
public class SeparateChainingHashST<Key extends Comparable<Key>,Value> {
    private int N;
    private int M;
    private SequentialSearchST<Key,Value>[] st;

    public SeparateChainingHashST(){
        this(997);
    }

    public SeparateChainingHashST(int M){
        this.M=M;
        st = new SequentialSearchST[M];
        for(int i=0;i<M;i++){
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key){
        return (key.hashCode()&0x7fffffff)%M;
    }

    public Value get(Key key){
        return st[hash(key)].get(key);
    }

    public void put(Key key,Value value){
        st[hash(key)].put(key,value);
    }


}
