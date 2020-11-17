package chapter04;


import chapter01.Queue;
import chapter02.IndexMinPQ;

/**
 * @Classname PrimMST
 * @Description Prim算法的即时版本
 * @Date 2020/11/15 16:33
 * @Created by laohuang
 */
public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for(int v=0;v<G.V();v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        pq = new IndexMinPQ<>(G.V());

        distTo[0] = 0;
        pq.insert(0,0.0);
        while (!pq.isEmpty()){
            visit(G,pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G,int v){
        marked[v] = true;
        for(Edge e:G.adj(v)){
            int w = e.other(v);

            if(marked[w]){
                continue;
            }
            if(e.weight()<distTo[w]){
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if(pq.contain(w)){
                    pq.change(w,distTo[w]);
                }else {
                    pq.insert(w,distTo[w]);
                }
            }
        }
    }


    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }


    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight();
        return weight;
    }
}
