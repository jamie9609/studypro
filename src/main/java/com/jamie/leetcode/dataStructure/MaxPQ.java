package com.jamie.leetcode.dataStructure;

/**
 *
 * 二叉堆就是一种完全二叉树，所以适合存储在数组中，而且二叉堆拥有一些特殊性质。
 *
 * 二叉堆的操作很简单，主要就是上浮和下沉，来维护堆的性质（堆有序），核心代码也就十行。
 *
 * 优先级队列是基于二叉堆实现的，主要操作是插入和删除。插入是先插到最后，然后上浮到正确位置；删除是调换位置后再删除，然后下沉到正确位置。核心代码也就十行。
 * @PackageName: com.jamie.leetcode.dataStructure
 * @ClassName: MaxPQ
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/3 5:00 下午
 */
public class MaxPQ<K extends Comparable<K>> {
    // 存储元素的数组
    private K[] pq;
    // 当前 Priority Queue 中的元素个数
    private int N = 0;

    // 父节点的索引
    int parent(int root) {
        return root / 2;
    }
    // 左孩子的索引
    int left(int root) {
        return root * 2;
    }
    // 右孩子的索引
    int right(int root) {
        return root * 2 + 1;
    }

    public MaxPQ(int cap) {
        // 索引 0 不用，所以多分配一个空间
        pq = (K[]) new Comparable[cap + 1];
    }

    /* 返回当前队列中最大元素 */
    public K max() {
        return pq[1];
    }

    /* 插入元素 e */
    public void insert(K e) {
        // 直接放在堆底，然后让其上浮到合适的位置。
        N ++;
        pq[N] = e;
        swim(N);
    }

    /* 删除并返回当前队列中最大元素 */
    public K delMax() {
        K top = pq[1];
        exch(1, N);
        pq[N] = null;
        N --;
        sink(1);
        return top;
    }

    /* 上浮第 k 个元素，以维护最大堆性质 */
    private void swim(int k) {
        while ( k > 1 && less(parent(k), k)) {
            exch(parent(k), k);
            k = parent(k);
        }
    }

    /* 下沉第 k 个元素，以维护最大堆性质 */
    private void sink(int k) {
        while (left(k) <= N) {
            int larger = left(k);
            if ( right(k) <= N && less(larger, right(k)) ) {
                larger = right(k);
            }
            // 结点 k 比俩孩子都大，就不必下沉了
            if (less(larger, k))  {
                break;
            }
            // 否则，不符合最大堆的结构，下沉 k 结点
            exch(k, larger);
            k = larger;
        }
    }

    /* 交换数组的两个元素 */
    private void exch(int i, int j) {
        K temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /* pq[i] 是否比 pq[j] 小？ */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

}
