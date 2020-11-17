package chapter04;

import chapter01.Queue;
import chapter02.MinPQ;

/**
 * @Classname LazyPrimMST
 * @Description Prim算法的延时实现
 * @Date 2020/11/15 10:04
 * @Created by laohuang
 */
public class LazyPrimMST {

    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G){
        pq = new MinPQ<>();
        marked = new boolean[G.V()];
        mst = new Queue<>();

        // 必须保证G是连通的
        visit(G,0);

        while (!pq.isEmpty()){
            Edge e = pq.delMin();

            int v = e.either(),w = e.other(v);

            if(marked[v]&&marked[w]){
                continue;
            }
            mst.enqueue(e);
            if(!marked[v]){
                visit(G,v);
            }
            if(!marked[w]){
                visit(G,w);
            }
        }

    }


    private void visit(EdgeWeightedGraph G,int v){
        marked[v]=true;
        for(Edge e:G.adj(v)){
            if(!marked[e.other(v)]){
                pq.insert(e);
            }
        }
    }


    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        double sum = 0;
        for(Edge edge:edges()){
            sum+=edge.weight();
        }
        return sum;
    }





}
