package chapter04;

/**
 * @Classname IGraph
 * @Description 无向图api
 * @Date 2020/11/10 11:06
 * @Created by laohuang
 */
public interface IGraph {
    int V();
    int E();
    // 加入边
    void addEdge(int v,int w);
    // v相连的所有顶点
    Iterable<Integer> adj(int v);
    String toString();
}
