package chapter04;

import chapter01.Bag;

/**
 * @Classname EdgeWeightedGraph
 * @Description TODO
 * @Date 2020/11/15 9:13
 * @Created by laohuang
 */
public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V){
        this.V = V;
        this.E =0;
        adj = (Bag<Edge>[])new Bag[V];
        for(int v=0;v<V;v++){
            adj[v] = new Bag<>();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public Iterable<Edge> edges(){
        Bag<Edge> b = new Bag<>();
        for(int v=0;v<V;v++){
            for(Edge e:adj[v]){
                if(e.other(v)>v){
                    b.add(e);
                }
            }
        }
        return b;
    }

}
