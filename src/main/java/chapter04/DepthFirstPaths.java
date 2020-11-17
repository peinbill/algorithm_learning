package chapter04;

import chapter01.Stack;

/**
 * @Classname DepthFirstPaths
 * @Description TODO
 * @Date 2020/11/10 15:52
 * @Created by laohuang
 */
public class DepthFirstPaths implements IPaths{
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph G,int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G,s);
    }

    private void dfs(Graph G,int v){
        marked[v]=true;
        for(int w:G.adj(v)){
            if(!marked[w]){
                edgeTo[w]=v;
                dfs(G,w);
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
