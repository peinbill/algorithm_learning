package chapter02;

/**
 * @Classname Insertion
 * @Description 插入排序——当倒置的数量很少的时候，该算法可能比其他任意算法都快
 * @Date 2020/10/30 15:46
 * @Created by laohuang
 */
public class Insertion extends Example{

    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i=1;i<N;i++){
            for(int j=i;j>0&&less(j,j-1);j--){
                exch(a,j,j-1);
            }
        }
    }
}
