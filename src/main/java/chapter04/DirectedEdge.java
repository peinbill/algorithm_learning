package chapter04;

/**
 * @Classname DirectedEdge
 * @Description 加权有向边
 * @Date 2020/11/15 23:49
 * @Created by laohuang
 */
public class DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;

    public DirectedEdge(int v,int w,double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight(){
        return weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }



}
