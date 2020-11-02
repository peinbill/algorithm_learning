package chapter02;

import edu.princeton.cs.algs4.Stopwatch;

import java.awt.*;

/**
 * @Classname SortCompare
 * @Description TODO
 * @Date 2020/10/30 16:06
 * @Created by laohuang
 */
public class SortCompare {
    public static double time(String alg,Comparable[] a){
        Stopwatch timer = new Stopwatch();

        if(alg.equals("Insertion")){
            Insertion.sort(a);
        }else if(alg.equals("Selection")){
            Selection.sort(a);
        }else if(alg.equals("Merge")){
            Merge.sort(a);
        }else if(alg.equals("Heap")){
            Heap.sort(a);
        }else if(alg.equals("Quick")){
            Quick.sort(a);
        }else if(alg.equals("Shell")){
            Shell.sort(a);
        }

        return timer.elapsedTime();
    }

}
