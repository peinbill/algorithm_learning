package chapter02;

import edu.princeton.cs.algs4.StdRandom;

/**
 * @Classname Quick3way
 * @Description TODO
 * @Date 2020/11/2 16:21
 * @Created by laohuang
 */
public class Quick3way extends Example{

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a,int lo,int hi){
        if(hi<=lo){
            return;
        }
        int i=lo+1,lt=lo,gt=hi;
        Comparable v = a[lo];

        while (i<=gt){
            int cmp = a[i].compareTo(v);
            if (cmp==0){
                i++;
            }else if(cmp<0){
                exch(a,lt++,i++);
            }else if(cmp>0){
                exch(a,i,gt--);
            }
        }

        sort(a,lo,lt-1);
        sort(a,gt+1,hi);

    }
}
