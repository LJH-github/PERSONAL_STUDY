package com.lijinghai.lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * List集合
 *      ==>线程不安全
 *      出现异常：java.util.ConcurrentModificationException
 * 解决方案：
 *      1：Vector  ==>可以解决，但一般不使用，过于古老 JDK1.0
 *      2.Collections工具类中提供synchronizedList类  ==>可以解决，不常用
 *      3.CopyOnWriteArrayList  ==>写时复制技术
 *          原理：读的时候时是“并发读”，写是”独立写“，即当需要写入新的内容时：
 *          首先将原来的集合复制一份，向里面写入内容，当写完成后，将复制的集合和原来的集合合并，
 *          此时并发读取新的集合。因此避免并发修改异常
 * ==================================================================================================
 *
 * HashSet集合
 *      ==>线程不安全
 *      出现异常：java.util.ConcurrentModificationException
 * 解决方案：
 *      1.CopyOnWriteArraySet
 * ==================================================================================================
 *
 *
 * HashMap
 *      ==>线程不安全
 *      出现异常：java.util.ConcurrentModificationException
 * 解决方案：
 *      1.ConcurrentHashMap
 */

public class ThreadDemo4 {

    public static void main(String[] args) {

        //创建list集合
//        List list = new ArrayList<>();   //出现ConcurrentModificationException异常

        //1.使用Vector解决问题
//        List list = new Vector();

        //2.使用Collections工具类解决问题
//        List list = Collections.synchronizedList(new ArrayList<>());

        //3.使用CopyOnWriteArrayList解决问题
//        List list = new CopyOnWriteArrayList();

//        for (int i = 1; i <= 100; i++) {
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString().substring(0, 6));
//                System.out.println(list);
//            }, String.valueOf(i)).start();
//        }
//
//===================================================================================================================

        //创建Set集合
//        Set set = new HashSet();  //出现ConcurrentModificationException异常

        //1.使用CopyOnWriteArraySet解决问题
//        Set set = new CopyOnWriteArraySet();
//
//        for (int i = 1; i <= 40; i++) {
//            new Thread(() -> {
//                set.add(UUID.randomUUID().toString().substring(0, 6));
//                System.out.println(set);
//            }, String.valueOf(i)).start();
//        }

//==================================================================================================================

        //创建HashMap
//        Map map = new HashMap();   //出现ConcurrentModificationException异常

        //1.使用ConcurrentHashMap解决问题
        Map map = new ConcurrentHashMap();
        for (int i = 1; i <= 40; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                map.put(key,UUID.randomUUID().toString().substring(0, 6));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }
}
