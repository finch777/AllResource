package com.levi.algorithm_practice.algorithm;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * 收集雨水，一维
 */
public class CollectRain {

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(10);
        arr.add(0);
        arr.add(1);
        arr.add(0);
        arr.add(2);
        arr.add(1);
        arr.add(0);
        arr.add(1);
        arr.add(3);
        arr.add(2);
        arr.add(1);
        arr.add(2);
        arr.add(1);

        System.out.println(s1(arr));
        System.out.println(s2(arr));
        System.out.println(s3(arr));


    }

    /**
     * 暴力解
     * @param arr
     * @return
     */
    private static int s1(ArrayList<Integer> arr) {
        int n = arr.size();
        int res = 0;
        for (int i = 0; i < n; i++) {
            int l_max = 0, r_max = 0;
            for (int j = i; j < n; j++) {
                r_max = max(r_max, arr.get(j));
            }
            for (int j = i; j >= 0; j --) {
                l_max = max(l_max, arr.get(j));
            }
            res += min(l_max, r_max) - arr.get(i);
        }
        return res;
    }

    /**
     * 备忘录
     * @param arr
     * @return
     */
    private static int s2(ArrayList<Integer> arr) {

        int n = arr.size();
        int res = 0;
        //数组作为备忘录
        int[] l_max = new int[n];
        int[] r_max = new int[n];
        // 初始化
        l_max[0] = arr.get(0);
        r_max[n-1] = arr.get(n-1);
        //l_max表示<=i的最大值 从左往右更新
        for (int i = 1 ; i < n; i++) {
            l_max[i] = max(arr.get(i), l_max[i-1]);
        }
        //r_max表示>=i的最大值 从右往左更新
        for (int i = n-2 ; i >= 0; i--) {
            r_max[i] = max(arr.get(i), r_max[i+1]);
        }

        for (int i = 0; i < n; i++) {
            res += min(l_max[i], r_max[i]) - arr.get(i);
        }

        return res;
    }

    /**
     * 双指针
     * @param arr
     * @return
     */
    private static int s3(ArrayList<Integer> arr){

        int n = arr.size(), res = 0;

        int l_max = arr.get(0), r_max = arr.get(n-1);

        int left = 0, right = n-1;

        while (left <= right) {
            l_max = max(l_max, arr.get(left));
            r_max = max(r_max, arr.get(right));

            if (l_max < r_max) {
                res += l_max - arr.get(left);
                left ++;
            } else {
                res += r_max - arr.get(right);
                right --;
            }

        }

        return res;
    }


}
