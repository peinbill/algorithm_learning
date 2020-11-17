package chapter04;

/**
 * @Classname DepthFirstSearch
 * @Description TODO
 * @Date 2020/11/10 15:05
 * @Created by laohuang
 */
public class DepthFirstSearch implements ISearch {
    private boolean[] marked;
    private int count;
    public DepthFirstSearch(Graph G,int s){
        marked = new boolean[G.V()];
        dfs(G,s);
    }
    private void dfs(Graph G,int v){
        marked[v] = true;
        count++;
        for(int w:G.adj(v)){
            if(!marked[w]){
                dfs(G,w);
            }
        }
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    public int count(){
        return count;
    }
}
