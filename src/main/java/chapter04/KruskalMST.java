package chapter04;

import chapter01.Queue;
import chapter01.UF;
import chapter02.MinPQ;

/**
 * @Classname KruskalMST
 * @Description 克鲁斯卡尔算法
 * @Date 2020/11/15 23:23
 * @Created by laohuang
 */
public class KruskalMST {

    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G){
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for(Edge e:G.edges()){
            pq.insert(e);
        }
        UF uf = new UF(G.V());
        while (!pq.isEmpty()&&mst.size()<G.V()-1){
            Edge e =pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if(uf.connected(v,w)){
                // 会构造成环
                continue;
            }

            uf.union(v,w);
            mst.enqueue(e);
        }

    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weights(){
        double sum = 0;
        for(Edge edge:mst){
            sum+=edge.weight();
        }
        return sum;
    }


}
