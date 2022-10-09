package com.levi.allresource.algorithm;

import static java.lang.Math.min;

/**
 * �㷨С�� ������Ӵ�
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
            // �� s[i] Ϊ���ĵ�������Ӵ�
            String s1 = palindrome(s, i, i);
            // �� s[i] �� s[i+1] Ϊ���ĵ�������Ӵ�
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    public static String palindrome(String s, int l, int r) {
        // ��ֹ����Խ��
        while (l >= 0 && r < s.length()
                && s.charAt(l) == s.charAt(r)) {
            // ������չ��
            l--; r++;
        }
        // ������ s[l] �� s[r] Ϊ���ĵ�����Ĵ�;��Ϊ��ʱlָ��������-1��λ�ã�����Ҫ��1��rָ������Ҳ�+1��λ�ã�����Ϊ����ҿ� ������
        return s.substring(l + 1, r);
    }

    /**
     *�������㷨
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
        //���ԭ�ַ�������#��Ľ����
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
