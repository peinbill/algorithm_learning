package chapter02;


/**
 * @Classname MaxPQ
 * @Description 最大优先队列——堆实现。
 * 优先队列：有插入和删除最大元素的抽象数组类型
 * 堆：每个结点都大于等于子结点的具体数组结构
 * @Date 2020/11/2 20:15
 * @Created by laohuang
 */
public class MaxPQ<Key extends Comparable<Key>>{

    private Key[] pq;
    private int N=0;

    public MaxPQ(int maxN){
        pq = (Key[]) new Object[maxN+1];
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }

    private void exch(int i,int j){
        Key t = pq[i];
        pq[i]=pq[j];
        pq[j]=t;
    }

    private void swim(int k){
        while (k>1&&less(k/2,k)){
            exch(k,k/2);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k<=N){
            int j = 2*k;
            if(j<N&&less(j,j+1)){
                j++;
            }
            if(!less(k,j)){
                break;
            }
            exch(k,j);
            k=j;
        }
    }

    public void insert(Key key){
        pq[++N] = key;
        swim(N);
    }

    public Key deleteMax(){
        Key max = pq[1];
        exch(1,N--);
        pq[N+1] = null;// 使其对象游离
        sink(1);
        return max;

    }



}
