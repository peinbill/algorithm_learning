package chapter02;

/**
 * @Classname Shell
 * @Description 希尔排序：插入排序的进化
 * @Date 2020/10/30 16:08
 * @Created by laohuang
 */
public class Shell extends Example{

    public static void sort(Comparable[] a){
        int N = a.length;
        int h =1;

        // 先选取h的最大值
        while (h<N/3){
            h = 3*h+1;
        }

        while (h>=1){
            for(int i=h;i<N;i++){
                for(int j=i;j>=h&&less(j,j-h);j-=h){
                    exch(a,j,j-h);
                }
            }
            h=h/3;
        }

    }
}
