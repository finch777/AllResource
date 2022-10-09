package com.levi.allresource.testdisruptor.perf;

public class ParaChecker {
    /**
     * ��������Ч�ԣ�������
     * create on 2003-12-26 9:28:40 by jrliu
     *
     * @param para
     * @return ���ò�����Ч������TRUE�����򷵻�FALSE
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
