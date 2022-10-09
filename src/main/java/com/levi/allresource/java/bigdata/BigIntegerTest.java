package com.levi.allresource.java.bigdata;

import java.math.BigInteger;

/**
 * @author Levi Wang
 * @create 2022-10-09 12:50
 */

public class BigIntegerTest {
    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("9");

        BigInteger gcd = bigInteger.gcd(new BigInteger("12"));

        System.out.println(gcd);

    }
}
