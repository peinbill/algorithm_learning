package chapter04;

/**
 * @Classname Edge
 * @Description TODO
 * @Date 2020/11/15 9:05
 * @Created by laohuang
 */
public class Edge implements Comparable<Edge>{

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v,int w,double weight){
        this.v=v;
        this.w=w;
        this.weight=weight;
    }

    public double weight(){
        return weight;
    }

    public int either(){
        return v;
    }

    public int other(int vertex){
        if(vertex == v){
            return w;
        }else if(vertex==w){
            return v;
        }else {
            throw new RuntimeException("Inconsistent edge");
        }
    }


    public int compareTo(Edge that){
        if(this.weight()<that.weight){
            return -1;
        }else if(this.weight()>that.weight()){
            return 1;
        }else {
            return 0;
        }
    }
}
