package com.jamie.leetcode.binarySearch;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 田忌赛马
 * 给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
 * 返回 A 的任意排列，使其相对于 B 的优势最大化。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/advantage-shuffle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @PackageName: com.jamie.leetcode.binarySearch
 * @ClassName: tianjisaima
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/6 7:57 下午
 */
public class Tianjisaima {

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // 给 nums2 降序排序
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(
                (int[] a, int[] b) -> {return b[1] - a[1];}
        );


        for (int i = 0; i < n ; i ++) {
            priorityQueue.offer(new int[]{i, nums2[i]});
        }

        // 给 nums1 升序排序，用来赛马的顺序。
        Arrays.sort(nums1);

        int left = 0, right = n - 1;
        int[] res = new int[n];

        while ( !priorityQueue.isEmpty()) {
            int[] pair = priorityQueue.poll();
            int i = pair[0];
            int maxVal = pair[1];

            if (maxVal < nums1[right]) {
                res[i] = nums1[right];
                right--;
            } else {
                res[i] = nums1[left];
                left ++;
            }
        }
        return res;
    }
}
