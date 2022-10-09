package com.levi.algorithm_practice.algorithm;

import static java.lang.Math.min;

/**
 * 算法小抄 最长回文子串
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String s = "afbkvvkbdfvibhls";

        System.out.println(longestPalindrome(s));
        System.out.println(malache(s));
    }


    public static String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    public static String palindrome(String s, int l, int r) {
        // 防止索引越界
        while (l >= 0 && r < s.length()
                && s.charAt(l) == s.charAt(r)) {
            // 向两边展开
            l--; r++;
        }
        // 返回以 s[l] 和 s[r] 为中心的最长回文串;因为此时l指向答案最左侧-1的位置，所以要加1；r指向答案最右侧+1的位置，单因为左闭右开 抵消了
        return s.substring(l + 1, r);
    }

    /**
     *马拉车算法
     */
    public static int malache(String s){
        int N = s.length();
        int leng = 2 * N + 1;
        int maxLen = 0;

        char[] str = new char[leng];
        str[0] = '#';
        for (int i = 0; i < N; i ++){
            str[i * 2 + 1] = s.charAt(i);
            str[i * 2 + 2] = '#';
        }
        //输出原字符串加上#后的结果串
//        System.out.println(chars);

        int[] P = new int[leng];
        int c = 0, r = 0;

        for (int i = 0; i < leng; i++) {

            int mirror = (2 * c) -i;
            if (i < r) {
                P[i] = min(r-i, P[mirror]);
            }
            int a = i + (1 + P[i]);
            int b = i - (1 + P[i]);

            while (a < leng && b >= 0 && str[a] == str[b]){
                P[i] += 1;
                a ++;
                b --;
            }

            if (i + P[i] > r) {
                c = i;
                r = i + P[i];
                if (P[i] > maxLen) maxLen = P[i];
            }

        }

        return maxLen;
    }


}
