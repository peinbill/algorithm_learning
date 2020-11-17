package chapter04;

import chapter01.Queue;

/**
 * @Classname BellmanFordSP
 * @Description Bellman-Ford算法
 * @Date 2020/11/17 16:31
 * @Created by laohuang
 */
public class BellmanFordSP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private Queue<Integer> queue;
    private boolean[] onQ;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph G,int s){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        queue = new Queue<>();
        for(int v=0;v<G.V();v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty()&&!hasNegativeCycle()){
            int v = queue.dequeue();
            onQ[v] = false;
            relax(G,v);
        }

    }

    private void relax(EdgeWeightedDigraph G,int v){
        for(DirectedEdge e:G.adj(v)){
            int w = e.to();
            if(distTo[w]>distTo[v]+e.weight()){
                distTo[w] = distTo[v]+e.weight();
                edgeTo[w] =e;
                if(!onQ[w]){
                    queue.enqueue(w);
                    onQ[w]=true;
                }
            }
            if(cost++% G.V()==0){
                findNegativeCycle();
            }


        }
    }

    public boolean hasNegativeCycle(){
        return cycle!=null;
    }

    private void findNegativeCycle(){
        int V = edgeTo.length;
        EdgeWeightedDigraph spt;
        spt = new EdgeWeightedDigraph(V);
        for(int v=0;v<V;v++){
            if(edgeTo[v]!=null){
                spt.addEdge(edgeTo[v]);
            }
        }

        // 只能检测到环，不一定是负向的
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();



    }

    public Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }

}
