package com.levi.algorithm_practice.testdisruptor.perf;

public class ParaChecker {
    /**
     * 检查参数有效性（单个）
     * create on 2003-12-26 9:28:40 by jrliu
     *
     * @param para
     * @return 若该参数有效，返回TRUE；否则返回FALSE
     */
    public static boolean isValidPara(String para) {
        if (para == null || para.equals(""))
            return false;
        else
            return true;
    }

    public static boolean isValidPara(int para) {
        if (para == Integer.MIN_VALUE)
            return false;
        else
            return true;
    }

    public static boolean isValidPara(long para) {
        if (para == Long.MIN_VALUE)
            return false;
        else
            return true;
    }

    public static boolean isNull(String para) {
        if (para == null || para.equals(""))
            return true;
        else
            return false;
    }

    public static boolean isNull(Integer para) {
        if (para == null || para == Integer.MAX_VALUE || para == Integer.MIN_VALUE)
            return true;
        else
            return false;
    }

    public static boolean isNull(Long para) {
        if (para == null || para == Long.MAX_VALUE || para == Long.MIN_VALUE)
            return true;
        else
            return false;
    }
}
