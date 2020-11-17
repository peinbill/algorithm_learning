package chapter04;

import chapter01.Stack;

/**
 * @Classname DepthFirstDirectedPaths
 * @Description TODO
 * @Date 2020/11/12 16:17
 * @Created by laohuang
 */
public class DepthFirstDirectedPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstDirectedPaths(Digraph G,int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G,s);
    }

    private void dfs(Digraph G,int v){
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
