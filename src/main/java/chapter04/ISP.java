package chapter04;


import chapter01.Stack;

/**
 * @Classname ISP
 * @Description 最短路径api
 * @Date 2020/11/16 14:47
 * @Created by laohuang
 */
public abstract class ISP {
    double[] distTo;
    DirectedEdge[] edgeTo;

    double distTo(int v){
        return distTo[v];
    }

    boolean hasPathTo(int v){
        return distTo[v]<Double.POSITIVE_INFINITY;
    }

    Iterable<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for(DirectedEdge e=edgeTo[v];e!=null;e=edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }


    // 边的松弛
    public void relax(DirectedEdge e){
        int v=e.from(),w=e.to();
        if(distTo[w]>distTo[v]+e.weight()){
            distTo[w] = distTo[v]+e.weight();
            edgeTo[w]=e;
        }
    }

    // 顶点的松弛
    public void relax(EdgeWeightedDigraph G,int v){
        for(DirectedEdge e:G.adj(v)){
            int w = e.to();
            if(distTo[w]>distTo[v]+e.weight()){
                distTo[w] = distTo[v]+e.weight();
                edgeTo[w]=e;
            }
        }
    }
}
