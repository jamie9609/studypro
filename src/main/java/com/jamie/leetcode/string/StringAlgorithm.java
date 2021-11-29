package com.jamie.leetcode.string;

import sun.nio.cs.ext.MacHebrew;

import java.util.LinkedList;

/**
 * @PackageName: com.jamie.leetcode.string
 * @ClassName: stringAlgorithm
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/27 8:39 下午
 */
public class StringAlgorithm {

    public static String convert(String s, int numRows) {
        /*if (numRows == 1) {
            return s;
        }*/
        LinkedList[] lists = new LinkedList[numRows];

        for (int a = 0; a < numRows; a ++) {
            lists[a] = new LinkedList<>();
        }

        boolean isUp = false;
        int i = 0;
        int pass = -1;
        while ( i < s.length() ) {
            if (!isUp) {
                for (int j = 0; j < numRows; j ++) {
                    if (j == pass) {
                        continue;
                    }
                    lists[j].add(s.charAt(i));
                    i ++;
                    if (i >= s.length()) {
                        break;
                    }
                }
                pass = numRows -1;
                isUp = true;
                continue;
            }
            if (isUp) {
                for (int x = numRows - 1; x >= 0; x --) {
                    if (x == pass) {
                        continue;
                    }
                    lists[x].add(s.charAt(i));
                    i ++;
                    if (i >= s.length()) {
                        break;
                    }
                }
                pass = 0;
                isUp = false;
            }
        }

        StringBuilder charStr = new StringBuilder();
        for (int m = 0; m < numRows; m ++) {
            while (!lists[m].isEmpty()) {
                charStr.append(lists[m].removeFirst().toString());

            }
        }
        return charStr.toString();
    }

    public static void main(String[] args) {

        System.out.println(convert("abcdsfg",1));
    }
}
