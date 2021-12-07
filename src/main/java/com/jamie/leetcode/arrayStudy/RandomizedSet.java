package com.jamie.leetcode.arrayStudy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 随机删除/O(1)级别的删除，需要巧妙的做映射关系，把待删除的元素映射到目标元素身上。
 * 或者是利用好数组的特性。
 * @PackageName: com.jamie.leetcode.arrayStudy
 * @ClassName: RandomizedSet
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/6 9:34 下午
 */
class RandomizedSet {
    List<Integer> list;
    //记录每个元素对应在 list 中的索引
    HashMap<Integer, Integer> hashMap;

    public RandomizedSet() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
    }

    public boolean insert(int val) {
        if (hashMap.containsKey(val))
            return false;
        hashMap.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!hashMap.containsKey(val))
            return false;
        Integer sourceIndex = hashMap.get(val);
        Integer lastOneValue = list.get(list.size() - 1);
        // 先交换位置，即用末尾的值覆盖要删除的值。
        list.set(sourceIndex, lastOneValue);
        hashMap.put(lastOneValue, sourceIndex);
        //再删除末尾的值（待删除的值）
        list.remove(list.size() - 1);
        hashMap.remove(val);
        return true;
    }

    public int getRandom() {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }


    /**
     * 给定一个包含 [0，n) 中不重复整数的黑名单 blacklist ，写一个函数从 [0, n) 中返回一个不在 blacklist 中的随机整数。
     * @return
     */
    // 合法索引的数量
    static int end;
    // 黑名单中元素映射到合法的索引
    static HashMap<Integer, Integer> map;
    public static void solution(int n, int[] blacklist) {
        end = n - blacklist.length;
        map = new HashMap<>();
        int last = n - 1;
        // 初始化映射表
        for(int b : blacklist){
            map.put(b, 0);
        }
        for(int b : blacklist){
            // 本身就是无效索引
            if(b >= end){
                continue;
            }
            // 跳过不在黑名单的索引
            while(map.containsKey(last)){
                last--;
            }
            map.put(b, last--);
        }
    }

    public static int pick() {
        int index = (int)(Math.random()*end);
        return map.getOrDefault(index, index);
    }

    public static void main(String[] args) {
        solution(10, new int[]{1, 2});
        System.out.println(pick());
    }

}
