package chapter02;

import edu.princeton.cs.algs4.StdOut;

/**
 * @Classname Heap
 * @Description 堆排序——利用堆的数据结构——需要多次练习
 * @Date 2020/10/30 16:08
 * @Created by laohuang
 */
public class Heap {

    private Heap() { }

    public static void sort(Comparable[] pq) {
        int n = pq.length;

        // 构建堆
        for (int k = n/2; k >= 1; k--){
            sink(pq, k, n);
        }

        // 堆排序
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);//相当于删除了最大元素
            sink(pq, 1, k);
        }
    }


    private static void sink(Comparable[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)){
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

}
