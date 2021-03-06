package chapter04;

import chapter01.Queue;
import chapter01.Stack;

/**
 * @Classname BreadthFirstPaths
 * @Description 广度优先搜索
 * @Date 2020/11/10 16:06
 * @Created by laohuang
 */
public class BreadthFirstPaths implements IPaths{

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph G,int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G,s);
    }

    private void bfs(Graph G,int s){
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
