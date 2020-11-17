package chapter04;

import chapter02.IndexMinPQ;
import edu.princeton.cs.algs4.In;

/**
 * @Classname DijkstraSP
 * @Description DijkstraSP算法
 * @Date 2020/11/16 16:37
 * @Created by laohuang
 */
public class DijkstraSP extends ISP{

    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G,int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for(int v=0;v<G.V();v++){
            distTo[v] = Double.POSITIVE_INFINITY;
            pq.insert(s,0.0);
            while(!pq.isEmpty()){
                relax(G,pq.delMin());
            }
        }
    }


    @Override
    public void relax(EdgeWeightedDigraph G,int v){
        for(DirectedEdge e:G.adj(v)){
            int w = e.to();
            if(distTo[w]>distTo[v]+e.weight()){
                distTo[w] = distTo[v]+e.weight();
                edgeTo[w]=e;
                if(pq.contain(w)){
                    pq.change(w,distTo[w]);
                }else {
                    pq.insert(w,distTo[w]);
                }
            }
        }
    }

}
