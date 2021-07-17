package com.lijinghai.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ==>线程间“定制化”通信
 */

class ShareResource {

    // 标志位 1:aa线程 2:bb线程 3:cc线程
    private int flag = 1;

    //可重入锁
    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //打印5次
    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1) {
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "=>" + loop + "轮次");
            }
            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    //打印10次
    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "=>" + loop + "轮次");
            }
            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    //打印15次
    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "=>" + loop + "轮次");
            }
            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadDemo3 {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aa").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "bb").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "cc").start();

    }
}
