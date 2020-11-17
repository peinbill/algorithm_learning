package chapter04;

/**
 * @Classname TransitiveClosure
 * @Description 顶点对可达性
 * @Date 2020/11/14 15:57
 * @Created by laohuang
 */
public class TransitiveClosure {

    private DirectedDFS[] all;

    TransitiveClosure(Digraph G){
        all = new DirectedDFS[G.V()];
        for(int v=0;v<G.V();v++){
            all[v] = new DirectedDFS(G,v);
        }
    }


    boolean reachable(int v,int w){
        return all[v].marked(w);
    }


}
