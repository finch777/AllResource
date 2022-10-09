package com.levi.algorithm_practice.java.proxy;

public class UserDaoImpl implements UserDao {
    @Override
    public int add(int a, int b) {
        System.out.println("computing...");
        return a + b;
    }

    @Override
    public String update(String id) {
        System.out.println("updating...");
        return id;
    }
}
