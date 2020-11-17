package chapter04;

/**
 * @Classname ICC
 * @Description 连通分量
 * @Date 2020/11/11 10:52
 * @Created by laohuang
 */
public interface ICC {

    boolean connected(int v,int w);
    int count();
    int id(int v);

}
