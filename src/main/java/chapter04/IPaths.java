package chapter04;

/**
 * @Classname IPaths
 * @Description TODO
 * @Date 2020/11/10 15:51
 * @Created by laohuang
 */
public interface IPaths {
    boolean hasPathTo(int v);
    Iterable<Integer> pathTo(int v);
}
