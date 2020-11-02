package chapter02;

/**
 * @Classname Selection
 * @Description 选择排序：每次选择最小的元素
 * @Date 2020/10/30 15:30
 * @Created by laohuang
 */
public class Selection extends Example{

    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i=0;i<N;i++){
            int min = i;
            for(int j = i+1;j<N;j++){
                if(less(a[j],a[min])){
                    min = j;
                }
            }
            exch(a,i,min);
        }
    }

}
