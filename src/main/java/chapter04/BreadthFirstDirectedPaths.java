package chapter04;

import chapter01.Queue;
import chapter01.Stack;

/**
 * @Classname BreadthFirstDirectedPaths
 * @Description TODO
 * @Date 2020/11/12 16:18
 * @Created by laohuang
 */
public class BreadthFirstDirectedPaths implements IPaths{
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstDirectedPaths(Digraph G,int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G,s);
    }

    private void bfs(Digraph G,int s){
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        while (!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w:G.adj(v)){
                if(!marked[w]){
                    edgeTo[w]=v;
                    marked[w]=true;
                    queue.enqueue(w);
                }
            }
        }
    }


    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for(int x=v;x!=s;x=edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
