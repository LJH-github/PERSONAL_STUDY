package com.lijinghai.juc;

import java.util.concurrent.CountDownLatch;

/**
 * JUC中常用的辅助类：CountDownLatch
 * ==>减少计数，该类可以设置一个计数器
 * 使用countDown方法进行减1，使用await方法等待计数器不大于0，继续执行await方法后面的语句
 * <p>
 * 举例：六名同学陆续离开教室后，班长锁门
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        //不使用CountDownLatch时，会出现班长将同学锁在教室==>不可取
//        for (int i = 1; i < 7; i++) {
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName() + "号同学离开教室");
//            }, String.valueOf(i)).start();
//        }
//        System.out.println(Thread.currentThread().getName() + "班长锁门了");

// ===================================================================================================================

        //使用CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(6);   //设置初始值6
        for (int i = 1; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "号同学离开教室");
                //减一
                countDownLatch.countDown();

            }, String.valueOf(i)).start();
        }

        //等待减至0
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "==>班长锁门了");
    }

}
