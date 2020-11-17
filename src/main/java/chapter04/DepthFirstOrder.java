package chapter04;

import chapter01.Queue;
import chapter01.Stack;

/**
 * @Classname DepthFirstOrder
 * @Description 基于深度优先搜索的顶点排序
 * @Date 2020/11/13 10:52
 * @Created by laohuang
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;


    public DepthFirstOrder(Digraph G){
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[G.V()];

        for(int v=0;v<G.V();v++){
            if(!marked[v]){
                dfs(G,v);
            }
        }
    }


    public DepthFirstOrder(EdgeWeightedDigraph G){
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[G.V()];

        for(int v=0;v<G.V();v++){
            if(!marked[v]){
                dfs(G,v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        pre.enqueue(v);

        marked[v] = true;

        for(DirectedEdge edge:G.adj(v)){
            int w = edge.to();
            if(!marked[w]){
                dfs(G,w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    private void dfs(Digraph G,int v){
        pre.enqueue(v);

        marked[v] = true;

        for(int w:G.adj(v)){
            if(!marked[w]){
                dfs(G,w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);

    }


    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }



}
