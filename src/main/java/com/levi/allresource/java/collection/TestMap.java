package com.levi.allresource.java.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Levi Wang
 * @create 2022-10-01 11:35
 */

public class TestMap {
    public static void main(String[] args) {

        ConcurrentHashMap<Object, Object> map1 = new ConcurrentHashMap<>();

        HashSet<Object> objects = new HashSet<>();

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(3, 4);

//        for (Map.Entry entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }

//        for (Integer k : map.keySet()) {
//            System.out.println(k + " " + map.get(k));
//        }

//        //方式一：
//        Set entrySet = map.entrySet();
//        Iterator iterator1 = entrySet.iterator();
//        while (iterator1.hasNext()){
//            Object obj = iterator1.next();
//            //entrySet集合中的元素都是entry
//            Map.Entry entry = (Map.Entry) obj;
//            System.out.println(entry.getKey() + "---->" + entry.getValue());
//
//        }
//        System.out.println("/");
//
//        //方式二：
//        Set keySet = map.keySet();
//        Iterator iterator2 = keySet.iterator();
//        while(iterator2.hasNext()){
//            Object key = iterator2.next();
//            Object value = map.get(key);
//            System.out.println(key + "=====" + value);
//        }

//        map.forEach((k, v) -> {
//            System.out.println(k + " " + v);
//        });

//        map.entrySet().stream().forEach((entry) -> {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        });
//
//        // 多线程
//        map.entrySet().parallelStream().forEach((entry) -> {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        });

    }
}
