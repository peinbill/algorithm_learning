package chapter02;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * @Classname Merge
 * @Description 归并排序——面试的终点，要求熟练掌握
 * @Date 2020/10/30 16:08
 * @Created by laohuang
 */
public class Merge extends Example{
    private static Comparable[] aux;

    // 自上而下
    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a,0,a.length-1);
    }


    public static void sort(Comparable[] a,int lo,int hi){
        if(hi<=lo){
            return;
        }
        int mid = lo+(hi-lo)/2;
        sort(a,lo,mid);
        sort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }


    //自下而上（没有用到归并）
    public static void sort2(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for(int sz=1;sz<N;sz=sz+sz){
            for (int lo=0;lo<N-sz;lo+=2*sz){
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
            }
        }
    }


    // 归并排序的核心
    /**
     * @Description 归并操作
     * @Detail lo-mid 是有序的，mid+1-hi是有序的
     * @param a
     * @param lo
     * @param mid
     * @param hi
     * @Return void
     * @author huangjy
     * @date 2020/10/30 20:53
     */
    public static void merge(Comparable[] a,int lo,int mid,int hi){
        int i=lo,j=mid+1;
        for(int k= lo;k<=hi;k++){
            aux[k]=a[k];
        }

        for(int k=lo;k<=hi;k++){
            if(i>mid){
                // 此时左边的已经全部排完了
                a[k] = aux[j++];
            }else if(j>hi){
                // 此时右边的已经全部排完了
                a[k] = aux[i++];
            }else if(less(aux[j],aux[i])){
                a[k] = aux[j++];
            }else {
                a[k] = aux[i++];
            }
        }
    }


}
