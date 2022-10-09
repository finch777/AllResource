package com.levi.algorithm_practice.algorithm;

import java.util.Stack;

public class IsValidBrackets {
    public static void main(String[] args) {
        String s= "()(()";
        String s1 = "([{}])";
        System.out.println(isValid1(s));
        System.out.println(isValid2(s1));
    }

    /**
     * 只有小括号的情况
     * @param s
     * @return
     */
    public static boolean isValid1(String s) {
        char[] str = s.toCharArray();
        int left = 0;
        for (int i = 0; i < str.length; i ++) {
            if (str[i] == '('){
                left ++;
            } else {
                left --;
            }
            if (left == -1) {
                return false;
            }
        }
        return left == 0;
    }

    /**
     * 三种括号都有的情况
     * @param s
     * @return
     */
    public static boolean isValid2(String s){
        char[] str = s.toCharArray();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length; i ++) {
            if (str[i] == '(' || str[i] == '[' || str[i] == '{') {
                stack.push(str[i]);
            } else { // 字符 c 是右括号
                if (!stack.empty() && leftOf(str[i]) == stack.peek()){
                    stack.pop();
                } else {
                    // 和最近的左括号不匹配
                    return false;
                }
            }
        }
        // 是否所有的左括号都被匹配了
        return stack.empty();
    }

    public static char leftOf(char c){
        if (c == '}') return '{';
        if (c == ')') return '(';
        return '[';
    }

}
