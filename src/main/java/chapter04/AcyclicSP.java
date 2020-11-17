package chapter04;

import edu.princeton.cs.algs4.AcyclicLP;

/**
 * @Classname AcyclicSP
 * @Description 无环加权有向图的最短路径算法
 * @Date 2020/11/16 16:59
 * @Created by laohuang
 */
public class AcyclicSP extends ISP{

    public AcyclicSP(EdgeWeightedDigraph G,int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int v=0;v<G.V();v++){
            distTo[v]=Double.POSITIVE_INFINITY;
        }
        distTo[s]=0;

        Topological top = new Topological(G);
        for(int v:top.order()){
            relax(G,v);
        }

    }

}
