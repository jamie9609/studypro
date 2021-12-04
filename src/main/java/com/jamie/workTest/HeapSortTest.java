package com.jamie.workTest;


/**
 * @PackageName: com.jamie.workTest
 * @ClassName: HeapSortTest
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/3 5:43 下午
 */
public class HeapSortTest {

    /**
     * 堆排序，它最好和最坏的情况时间复杂度都是O(nlogn)，空间复杂度O(1)。
     * @param source
     * @return
     */

    public static int[] HeapSortDemo(int[] source) {
        if (source.length == 1){
            return source;
        }
        int n = source.length;

        //从第一个非叶子节点开始调整，先构建大顶堆
        for (int i = parentKey(n); i >= 0; i --) {
            dropAuto(source, i, n);
        }
        //将堆顶元素移动到最末尾，重排。
        for (int j = n - 1; j > 0; j --) {
            exchange(source, 0, j);
            dropAuto(source, 0, j);
        }

        return source;
    }

    /**
     * 下沉，从上到下，依次遍历。
     * @param ans
     */
    public static void dropAuto(int[] ans, int index, int length) {
        if (index < 0) {
            return;
        }
        // 默认当前节点（父节点）是最大值。
        int largestIndex = index;
        if (leftChildKey(index) <= length - 1 && ans[largestIndex] < ans[leftChildKey(index)] ) {
            largestIndex = leftChildKey(index);
        }

        if (rightChildKey(index) <= length - 1 && ans[largestIndex] < ans[rightChildKey(index)]) {
            largestIndex = rightChildKey(index);

        }

        if (largestIndex != index) {
            exchange(ans, index, largestIndex);
            dropAuto(ans, largestIndex, length);
        }
    }

    public static int leftChildKey(int root) {
        return root * 2 + 1;
    }
    public static int rightChildKey(int root) {
        return root * 2 + 2;
    }
    public static int parentKey(int root) {
        if (root == 0) {
            return -1;
        }
        return root / 2 - 1 / 2;
    }
    public static void exchange (int[] list, int index1, int index2 ) {
        int tmp = list[index1];
        list[index1] = list[index2];
        list[index2] = tmp;
    }

    /**
     * 找最大值。
     * @param args
     */
    public static void main(String[] args) {
        int n = 8;

        int[] case1 = new int[]{12,12,33333,1,2,23,4,5,13,1,10};
        //List<Integer> ints = Arrays.asList(case1).stream().collect(Collectors.toList());

        int[] integers = HeapSortDemo(case1);

        for (int i = 0; i < integers.length; i ++) {
            System.out.println(integers[i] + ",");
        }
    }
}
