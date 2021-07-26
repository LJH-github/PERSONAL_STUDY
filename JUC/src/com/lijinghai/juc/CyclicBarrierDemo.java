package com.lijinghai.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * JUC中常用的辅助类：CyclicBarrier 循环栅栏类
 *      ==>它是一个不断加1的过程
 *
 * 举例：
 *      集齐7颗龙珠召唤神龙显现
 */

public class CyclicBarrierDemo {

    //创建固定值
    private static final int NUMBER = 7;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("==>集齐7颗龙珠可召唤神龙现身！");
        });

        //集齐龙珠
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "星龙珠");
                    //等待龙珠集齐
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
