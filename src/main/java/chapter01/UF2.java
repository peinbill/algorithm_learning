package chapter01;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @Classname UF2
 * @Description quick-union方法
 * @Date 2020/10/29 10:22
 * @Created by laohuang
 */
public class UF2 {

    private int[] id;
    private int count;

    public UF2(int N){
        count = N;
        id = new int[N];
        for(int i = 0;i<N;i++){
            id[i]=i;
        }
    }

    public int count(){
        return count;
    }

    public boolean connected(int p,int q){
        return find(p)==find(q);
    }

    public int find(int p){
        while (p!=id[p]){
            p = id[p];
        }
        return p;
    }

    public void union(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot==qRoot){
            return;
        }
        id[pRoot]= qRoot;
        count--;
    }

    public static void main(String[] args){
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(uf.connected(p,q)){
                continue;
            }
            uf.union(p,q);
            StdOut.printf(p+" "+q);
        }
        StdOut.println(uf.count()+" components");
    }
}
