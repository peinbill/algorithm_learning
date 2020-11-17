package chapter04;

/**
 * @Classname Topological
 * @Description 拓扑排序
 * @Date 2020/11/13 11:05
 * @Created by laohuang
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph G){
        DirectedCycle cycle = new DirectedCycle(G);
        if(!cycle.hasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }
    public Topological(EdgeWeightedDigraph G) {
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }
    public Iterable<Integer> order(){
        return order;
    }

    public boolean isDAG(){
        return order!=null;
    }





}
